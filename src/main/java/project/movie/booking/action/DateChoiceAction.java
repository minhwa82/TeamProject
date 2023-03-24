package project.movie.booking.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.moive.booking.db.BookingDAO;

public class DateChoiceAction implements Action {

	@Override 
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : DateChoiceAction_execute() 호출 ");
		String M_num = request.getParameter("M_num");
		String Sc_num = request.getParameter("Sc_num");
		String T_date = request.getParameter("T_date");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BookingDAO dao = new BookingDAO();
		List timeList = dao.getTime(M_num, Sc_num, T_date);
		
		System.out.println(M_num);
		System.out.println(Sc_num);
		System.out.println(T_date);
		System.out.println(timeList);
		request.setAttribute("timeList", timeList);
		ActionForward forward = new ActionForward();
		forward.setPath("./booking/movieTime.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
