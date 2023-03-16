package movie.movie.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.db.MovieDAO;

public class TImeInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" TimeInsertAction() 실행 ");
		
		MovieDAO dao = new MovieDAO();
		
		List List = dao.getmovieAPI();
		
		request.setAttribute("List", List);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./movie/timeInsert.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
