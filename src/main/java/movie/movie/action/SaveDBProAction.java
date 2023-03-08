package movie.movie.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.db.MovieDAO;
import movie.movie.db.MovieDTO;

public class SaveDBProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" SaveDBProAction_execute() 실행 ");
		
		String[] M_num = request.getParameterValues("movieCd");
		String[] M_name = request.getParameterValues("movieNm");
		String[] M_playDate = request.getParameterValues("moviePlayDate");		
		
		MovieDTO dto = new MovieDTO();
		MovieDAO dao = new MovieDAO();
		
		for(int i=0; i<M_num.length;i++) {
			
			dto.setM_num(M_num[i]);
			dto.setM_name(M_name[i]);
			dto.setM_playDate(M_playDate[i]);
			dao.saveDB(dto);
		}
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./movieList.mv");
		forward.setRedirect(true);
		
		return forward;
	}

}
