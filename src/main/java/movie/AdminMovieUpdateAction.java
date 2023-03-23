package movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.db.MovieDAO;
import movie.db.MovieDTO;

public class AdminMovieUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		System.out.println(" AdminMovieUpdateAction_execute");
		MovieDTO dto = new MovieDTO();
		
		dto.setM_num(request.getParameter("M_num"));
		dto.setM_name(request.getParameter("M_name"));
		dto.setM_type(request.getParameter("M_type"));
		dto.setM_runTime(request.getParameter("M_runTime"));
		dto.setM_director(request.getParameter("M_director"));
		dto.setM_age(Integer.parseInt(request.getParameter("M_age")));
		dto.setM_actor(request.getParameter("M_actor"));
		dto.setM_playDate(request.getParameter("M_playDate"));
		dto.setM_explain(request.getParameter("M_explain"));
		dto.setM_grade(Double.parseDouble(request.getParameter("M_grade")));
		dto.setM_reservationRate(Double.parseDouble(request.getParameter("M_reservationRate")));
		
		MovieDAO dao = new MovieDAO();
		dao.updateMovie(dto);
		System.out.println(dto);
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminMovieList.mv");
		forward.setRedirect(true);
		return forward;
	}

}
