package movie.seat.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.seat.db.SeatDAO;

public class SeatChoiceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SeatChoiceAction_execute() 실행 ");
		
		String M_num = request.getParameter("M_num");
		String M_name = request.getParameter("M_mame");
		
		// 스크린번호, 시작시간, 상영날짜 받아야함
		// 받기전 임시
		
		String startTime = "09:00:00";
		String Sc_num = "02_1";
		String date = "2023-03-09";
		
		SeatDAO dao = new SeatDAO();
		List SList = dao.getSeatStatus(M_num, startTime, Sc_num, date);
		
		request.setAttribute("SList", dao);
		
		
		return null;
	}

}
