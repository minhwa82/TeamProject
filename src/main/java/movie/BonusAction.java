package movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class BonusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
								 HttpServletResponse response) throws Exception {
		System.out.println(" M : BonusAction_execute 호출");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		request.setAttribute("dto", dto);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./movie/bonus.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
