package project.movie.booking.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.moive.booking.db.BookingDAO;

public class MovieChoiceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BookingChoiceAction_execute() 호출 ");
		String M_num = request.getParameter("M_num");
		BookingDAO dao = new BookingDAO();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("M_num:" + M_num);
		List screenList =  dao.getScreen(M_num);
//		System.out.println(timeList);
//		request.setAttribute("timeList", timeList);
		System.out.println(screenList); 
		request.setAttribute("screenList", screenList);
		ActionForward forward = new ActionForward();
		forward.setPath("./booking/choiceMovie.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
