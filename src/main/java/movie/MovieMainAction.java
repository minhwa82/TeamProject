package movie;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.db.MovieDAO;
import movie.db.MovieDTO;

public class MovieMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		System.out.println("MovieMainAction_execute 호출");
		
		
		MovieDAO dao = new MovieDAO();
		
		List movieList = dao.MList();
		
		request.setAttribute("movieList", movieList);
		System.out.println(movieList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main/main.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
