package movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.main.db.MovieDAO;

public class MovieDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
	
		String M_num = request.getParameter("M_num");

		MovieDAO dao = new MovieDAO();
		dao.deleteMovie(M_num);
		
		System.out.println(M_num);
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminMovieList.mm");
		forward.setRedirect(true);
		
		return forward;
	}

}
