package movie;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDTO;
import movie.db.MovieDAO;


public class AdminMemberInfo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
								 HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		MovieDAO dao = new MovieDAO();
		
		request.setAttribute("memList", dao.getMemberList());
		
		
		forward.setPath("./admin_movie/adminMemberList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
