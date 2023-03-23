package movie;




import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import movie.db.MovieDAO;
import movie.db.MovieDTO;

public class MovieAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		System.out.println(" M : MovieAddAction 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 파일업로드 접근경로
				ServletContext ctx = request.getServletContext(); 
				String realPath = ctx.getRealPath("/upload");
				System.out.println(realPath);
				
				int maxSize = 10 * 1024 * 1024; // 10MB
				
				MultipartRequest multi 
				           = new MultipartRequest(
				        		   request,
				        		   realPath,
				        		   maxSize,
				        		   "UTF-8",
				        		   new DefaultFileRenamePolicy()
				        		   );
				System.out.println(" M : 첨부파일 업로드 성공! ");
				
				MovieDTO dto = new MovieDTO();
				dto.setM_num(multi.getParameter("M_num"));
				dto.setM_name(multi.getParameter("M_name"));
				dto.setM_type(multi.getParameter("M_type"));
				dto.setM_runTime(multi.getParameter("M_runTime"));
				dto.setM_director(multi.getParameter("M_director"));
				dto.setM_age(Integer.parseInt(multi.getParameter("M_age")));
				dto.setM_actor(multi.getParameter("M_actor"));
				dto.setM_explain(multi.getParameter("M_explain"));
				dto.setM_grade(Double.parseDouble(multi.getParameter("M_grade")));
				dto.setM_reservationRate(Double.parseDouble(multi.getParameter("M_reservationRate")));
				dto.setM_playDate(multi.getParameter("M_playDate"));
				
				String M_img = multi.getFilesystemName("M_img");
				
				dto.setM_img(M_img);
				MovieDAO dao = new MovieDAO();
				dao.insertMovie(dto);
				System.out.println(dto);
				
				forward.setPath("./AdminMovieList.mv");
				forward.setRedirect(true);
				
				
				return forward;
	}

}
