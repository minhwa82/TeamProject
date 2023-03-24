package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class MemberJoinCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M :  MemberJoinCheckAction_execute() 호출! ");
		
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); // 삭제예정
		// 전달정보 저장(파라미터)
		// MemberDTO객체 생성 -> 정보 저장 (확인)
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String mem_id = request.getParameter("mem_id"); // checkId 메서드 쓰는 용(안되면지울거임)
		String mem_email = request.getParameter("mem_email");
		String phone = request.getParameter("phone");
		
		//String year = request.getParameter("year");
		//String month = request.getParameter("month");
		//String day = request.getParameter("day");
		String name = request.getParameter("mem_name");
		//String birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		
		//String postcode = request.getParameter("postcode");
		
	
		
		
	
		//(각각의 값만 확인하기 위함, submit할 때도 id 등 값이 넘어가는데 중복체크로 넘어가면 안됨)
	
			//System.out.println(mem_id);
			// 아이디 중복확인
			if(mem_id!=null) {
			int checkId = dao.check("Mem_id",mem_id);
			System.out.println("checkId 값 : "+checkId);
				//if(checkId==1) {
					response.getWriter().write(checkId+"");
					return null;
				//}
			}
			
			
			// 이메일 중복확인
			if(mem_email!=null) {
			int checkEmail = dao.check("Mem_email",mem_email);
			System.out.println("checkEmail 값 : "+checkEmail);
				response.getWriter().write(checkEmail+"");
				
					return null;
	
			}
			
			
			System.out.println(phone);
			//String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
			//System.out.println(phone);
			
			
			// 휴대폰 번호 중복확인
			if(phone!=null) {
			int checkPhone = dao.check("Mem_phone",phone);
			System.out.println("checkPhone 값 : "+checkPhone);
			
					response.getWriter().write(checkPhone+"");
					return null;
	
			}
		
		
	
	
		return null;
		
		
	}
		

}
