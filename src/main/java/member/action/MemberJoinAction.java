package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M :  MemberJoinAction_execute() 호출! ");
		
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8"); // 삭제예정
		// 전달정보 저장(파라미터)
		// MemberDTO객체 생성 -> 정보 저장 (확인)
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String mem_id = request.getParameter("mem_id"); // checkId 메서드 쓰는 용(안되면지울거임)
		String mem_email = request.getParameter("mem_email");
		String phone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("mem_name");
		String birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		
		String postcode = request.getParameter("postcode");
		
		System.out.println(name);
		
		
		
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
		dto.setMem_name(request.getParameter("mem_name"));
		dto.setMem_email(request.getParameter("mem_email"));
		dto.setMem_birth(birth);
		dto.setMem_phone(phone);
		dto.setMem_postcode(postcode);
		dto.setMem_addr1(request.getParameter("mem_addr"));
		dto.setMem_addr2(request.getParameter("detailAddress"));
		dto.setMem_addr3(request.getParameter("extraAddress"));
		

		
			
	
		
		System.out.println(" M : "+dto.toString());
		
		// 디비에 저장
		
		// 회원가입
		dao.insertMember(dto);
	

		
		
		// 로그인 페이지로 이동티켓 생성(직접이동x)
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);		
		
		return forward;
		
		
	}
		

}
