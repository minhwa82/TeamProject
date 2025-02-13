package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;


public class LoginAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		MemberDTO dto = new MemberDTO();
		dto.setMem_id(request.getParameter("id"));
		dto.setMem_pw(request.getParameter("pw"));
		dto.setMem_name(request.getParameter("name"));

		MemberDAO dao = new MemberDAO();
		int result = dao.loginMember(dto);
		
		if(result == 0) { // 비밀번호 오류
			
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("  alert('비밀번호가 맞지 않습니다.'); ");
			out.write("  history.back(); ");
			out.write("</script>");
			out.close();
			return null; 
			
		} if (result == -1) { // 회원정보 없음
		
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write(" alert('등록된 회원정보가 없습니다.');");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			out.write(" history.back(); ");
			out.write("</script>");
			out.close();
			return null; 
			
		} // result == 1 로그인 성공
		
		HttpSession session = request.getSession();
		session.setAttribute("dto", dto);
		session.setAttribute("id", dto.getMem_id());
		session.setAttribute("Mem_num", dto.getMem_num());
		System.out.println(dto);
		
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		ActionForward forward = new ActionForward();
		
		if (referer != null) {
			response.sendRedirect("./Booking.bk");
			
			return null;
			
		} else {
			forward.setPath("./Main.mm");
			forward.setRedirect(true);
			return forward;
		}
		
	}
	
}
