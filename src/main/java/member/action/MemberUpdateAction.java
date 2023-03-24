package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateAction_execute() 호출 ");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DB-특정 회원정보 조회
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberInfo(id);
		//dto.setAge(100);
		// request영역에 저장
		request.setAttribute("dto", dto);
		System.out.println(dto);
		//request.setAttribute("dto", dao.getMemberInfo(id));
		
		// 페이지이동 티켓
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}

}
