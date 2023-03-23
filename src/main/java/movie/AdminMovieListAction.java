package movie;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import API.NaverAPI;
import API.NaverAPIDTO;
import movie.main.db.MovieDAO;
import movie.main.db.MovieDTO;

public class AdminMovieListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminMovieListAction_execute() 실행 ");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MovieDAO dao = new MovieDAO();
		
		List movieList = dao.MList();
		
		request.setAttribute("movieList", movieList);
		
		forward.setPath("./admin_movie/adminMovieList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
