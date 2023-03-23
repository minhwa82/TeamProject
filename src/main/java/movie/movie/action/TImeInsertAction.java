package movie.movie.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.db.MovieDAO;
import project.movie.screen.db.ScreenDAO;

public class TImeInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" TimeInsertAction() 실행 ");
		
		MovieDAO dao = new MovieDAO();
		ScreenDAO sdao = new ScreenDAO();
		
		List List = dao.getmovieAPI();
		List sList = sdao.screenList();
		
		request.setAttribute("List", List);
		request.setAttribute("sList", sList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./movie/timeInsert.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
