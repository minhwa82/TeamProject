package movie;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import API.NaverAPI;
import API.NaverAPIDTO;

public class MovieDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" MovieDetailAction_execute 호출 ");
		
		String movieNm = request.getParameter("movieNm");
		String movieCd = request.getParameter("movieCd");
		
		NaverAPI api = new NaverAPI();
		NaverAPIDTO dto = api.getNaverAPI(movieNm);
//		ReviewDAO dao = new ReviewDAO();
//		List reviewList = dao.getMovieReview(movieCd);
		
//		request.setAttribute("reviewList", reviewList);
		
		request.setAttribute("userRating", dto.getUserRating());
		request.setAttribute("img", dto.getImg());
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./movie/movieDetail.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
