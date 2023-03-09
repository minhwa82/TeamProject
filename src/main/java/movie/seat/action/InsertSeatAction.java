package movie.seat.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.seat.db.SeatDAO;

public class InsertSeatAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : InsertSeatAction_execute() 실행 ");
		
		SeatDAO dao = new SeatDAO();
		
		for(int i=1; i<12; i++) {
			
			dao.insertSeat("e"+i, "02_1");
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("<script>");
		out.write("alert('시트등록!');");
		out.write("history.back();");
		out.write("</script>");
		out.close();
		
		return null;
	}

}
