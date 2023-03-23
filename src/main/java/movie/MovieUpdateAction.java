package movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.db.MovieDAO;
import movie.db.MovieDTO;

public class MovieUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		System.out.println(" AdminUpdateAction_execute 호출");
		
		String M_num = request.getParameter("M_num");
		
		MovieDAO dao = new MovieDAO();
		MovieDTO dto = dao.getMovieList(M_num);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin_movie/updateMovie.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
