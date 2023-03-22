package movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.db.MovieDAO;
import movie.db.MovieDTO;
import API.NaverAPI;
import API.NaverAPIDTO;

public class AdminBookingList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminBookingList_execute() 실행 ");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MovieDAO dao = new MovieDAO();
		
		List bookingList = dao.getBookingList();
		request.setAttribute("bookingList", bookingList);
		
		forward.setPath("./admin_movie/adminBookingList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
