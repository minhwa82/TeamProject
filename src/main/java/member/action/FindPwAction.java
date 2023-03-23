package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class FindPwAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : findPwAction_execute() 호출 ");
		
		String Mem_name = request.getParameter("Mem_name");
		String Mem_id = request.getParameter("Mem_id");
				
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.findPw(Mem_name,Mem_id);

		request.setAttribute("dto", dto);
						
		// 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
				
		ActionForward forward = new ActionForward();
				
		if(dto != null) {	
			
			forward.setPath("member/findPwResult.jsp");
			forward.setRedirect(false);
			return forward;
			
		}else {
			
			out.print("<script>");
			out.print(" alert('일치하는 정보가 없습니다.'); ");
			out.print(" location.href='./findId.me'; ");
			out.print("</script>");
			out.close();
					
			return null;
	
		}

	}

}
