package movie.movie.action;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.db.MovieDAO;
import movie.movie.db.TimeDTO;
import movie.seat.db.SeatDAO;

public class TimeInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" TimeInsertProAction_execute() 실행 ");

		String Sc_num = request.getParameter("Sc_num");
		String M_num = request.getParameter("M_num");

		TimeDTO dto = new TimeDTO();

		String date = request.getParameter("T_date");
		String startTime = request.getParameter("T_startTime");
		String endTime = request.getParameter("T_endTime");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date T_date = dateFormat.parse(date);
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		dto.setT_date(fd.format(T_date));
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date T_stime = timeFormat.parse(startTime);
		Date T_etime = timeFormat.parse(endTime);
		SimpleDateFormat td = new SimpleDateFormat("HH:mm");
		dto.setT_startTime(td.format(T_stime));
		dto.setT_endTime(td.format(T_etime));
		
		
		
		MovieDAO dao = new MovieDAO();
		int T_num = dao.insertTime(dto, M_num, Sc_num);
		SeatDAO sdao = new SeatDAO();
		
		for(int i=1; i<13; i++) {
			
			sdao.insertSeat("a0"+i, Sc_num, T_num);
			sdao.insertSeat("b0"+i, Sc_num, T_num);
			sdao.insertSeat("c0"+i, Sc_num, T_num);
			sdao.insertSeat("d0"+i, Sc_num, T_num);
			sdao.insertSeat("e0"+i, Sc_num, T_num);
		}
		
		
		

		return null;
	}

}
