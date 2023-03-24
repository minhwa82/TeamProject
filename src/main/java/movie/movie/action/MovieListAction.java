package movie.movie.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.movie.db.MovieDAO;
import movie.movie.db.MovieDTO;
import movie.movie.db.NaverAPIDTO;

public class MovieListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MovieListAction_execute() 실행 ");
		
		MovieDAO dao = new MovieDAO();
		MovieDTO dto = new MovieDTO();
		NaverAPIDTO Ndto = new NaverAPIDTO();
		NaverAPI api = new NaverAPI();
		
		List dailyList = dao.getmovieAPI();
		List imgList = new ArrayList<>();
		
		for(int i=0; i<dailyList.size();i++) {
			dto = (MovieDTO)dailyList.get(i);
			Ndto = api.getNaverAPI(dto.getM_name());
			imgList.add(Ndto.getImg());
		}
		
		
		request.setAttribute("dailyList", dailyList);
		request.setAttribute("imgList", imgList);

		ActionForward forward = new ActionForward();
		forward.setPath("./movie/movieList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
