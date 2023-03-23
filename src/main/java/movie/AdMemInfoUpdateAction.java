package movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDTO;
import movie.main.db.MovieDAO;

public class AdMemInfoUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
		int Mem_num = Integer.parseInt(request.getParameter("Mem_num"));
		
		MovieDAO dao = new MovieDAO();
		MemberDTO dto = dao.getMemberList(Mem_num);
		
		request.setAttribute("dto", dto);

		ActionForward forward = new ActionForward();
		forward.setPath("./admin_movie/adMemInfoUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	
	}

}
