package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달된 정보 저장(수정할 정보)
		MemberDTO dto = new MemberDTO();



		//String mem_id = request.getParameter("mem_id"); // checkId 메서드 쓰는 용(안되면지울거임)
		String mem_email = request.getParameter("mem_email");
		String phone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("mem_name");
		String birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		
		String postcode = request.getParameter("postcode");
		
		
		
	
		// 배열
		String[] type = request.getParameterValues("type");
		System.out.println(type);
		if(type==null) { //배열이 null일때
			dto.setMem_mType(null);		
		} else { // 배열의 값이 없을 때 .lenght 오류, null출력
			String mType = String.join(",", type);
			System.out.println(type.length);
			dto.setMem_mType(mType);
		}
		
		
		dto.setMem_id(request.getParameter("mem_id"));
		dto.setMem_pw(request.getParameter("mem_pw"));
		//dto.setMem_name(request.getParameter("mem_name"));
		dto.setMem_email(request.getParameter("mem_email"));
		dto.setMem_birth(birth);
		dto.setMem_phone(phone);
		dto.setMem_postcode(postcode);
		dto.setMem_addr1(request.getParameter("mem_addr"));
		dto.setMem_addr2(request.getParameter("detailAddress"));
		dto.setMem_addr3(request.getParameter("extraAddress"));
		

		
			
	
		
		System.out.println(" M : "+dto.toString());
		
		
		
		
		
		
		// DAO 객체  - 회원정보 수정()
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto);
		
		// 페이지 이동(JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0) {
			out.println("<script>");
			out.println(" alert('비밀번호 오류'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			return null;			
		}
		
		if(result == -1) {
			out.println("<script>");
			out.println(" alert(' 아이디없음 오류'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			return null;
		}	
		
		// result == 1
		out.println("<script>");
		out.println(" alert(' 수정완료! '); ");
		out.println(" location.href='./Main.mm'; ");
		out.println("</script>");
		out.close();
		
		return null;
	}

}
