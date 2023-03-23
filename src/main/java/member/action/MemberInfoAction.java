package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberInfoAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id == null) { // 로그인 안 됐을때
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 - 회원정보 조회()
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.memberInfo(id);
		
		// DB에서 가져온 DTO 정보를 request 영역에 저장
		request.setAttribute("dto", dto);
		System.out.println(dto);
		
		// 페이지 이동 -> 출력
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
