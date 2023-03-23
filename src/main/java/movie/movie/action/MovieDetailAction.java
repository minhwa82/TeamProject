package movie.movie.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.db.MovieDAO;
import movie.movie.db.NaverAPIDTO;
import movie.review.db.ReviewDAO;

public class MovieDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" MovieDetailAction_execute 호출 ");
		
		String movieNm = request.getParameter("movieNm");
		String movieCd = request.getParameter("movieCd");
		
		NaverAPI api = new NaverAPI();
		NaverAPIDTO dto = api.getNaverAPI(movieNm);
		ReviewDAO dao = new ReviewDAO();
		List reviewList = dao.getMovieReview(movieCd);
		
		request.setAttribute("reviewList", reviewList);
		
		request.setAttribute("userRating", dto.getUserRating());
		request.setAttribute("img", dto.getImg());
		
		MovieDAO Mdao = new MovieDAO();
		List tList = Mdao.getTime(movieCd);
		
		Date now = new Date();

		request.setAttribute("now", now);
		request.setAttribute("tList", tList);
		request.setAttribute("movieCd", movieCd);
		request.setAttribute("movieNm", movieNm);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./movie/movieDetail.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
