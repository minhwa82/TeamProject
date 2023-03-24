package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class KakaoLoginAction implements Action{

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		System.out.println(" M : KakaoLoginAction_execute() 호출 ");
		
			// 전달정보 저장
			String id = request.getParameter("Mem_id");
			String Mem_id = "K_" + id.substring(0,8);
			String Mem_pw = "kakao";
			String Mem_email = request.getParameter("Mem_email");
			
			MemberDTO dto = new MemberDTO();
			dto.setMem_name(request.getParameter("Mem_name"));

			System.out.println(Mem_id);
			System.out.println(Mem_email);
				
			// DAO객체 생성
			MemberDAO dao = new MemberDAO();
			int result = dao.kakaoLogin(Mem_id, Mem_email);
			
			ActionForward forward = new ActionForward();

			// 결과에 따른 페이지 이동
			if (result == -1) { // 비밀번호 오류
				
				System.out.println(" 비회원이다!!!!!!  ");
				dto.setMem_id(Mem_id);
				dto.setMem_pw(Mem_pw);
				dto.setMem_name(request.getParameter("Mem_name"));
				dto.setMem_email(Mem_email);
	//			dto.setMem_birth(request.getParameter("birthday"));  생일 컬럼 타입 오류 
	//			dto.setMem_phone(request.getParameter("mobile")); // 

				System.out.println(" M : DTO " + dto);
				
				dao.kakaoJoin(dto);
				System.out.println(" M : 카카오 회원가입 성공!!!!!!!!!!!!!!!!!!! ");
				
				// 로그인
				dao.kakaoLogin(Mem_id, Mem_email);
	
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getMem_id());
				forward.setPath("./Main.mm");
				forward.setRedirect(true);		
							
				return forward;	
					
			} else { // result == 1 로그인성공 -> 아이디 정보 session 영역 저장	
				
				System.out.println(" M : 카카오 로그인 성공!!!!!!!!!!!!!!!!!!! ");
			
				HttpSession session = request.getSession();
				session.setAttribute("id", Mem_id);	
				session.setAttribute("dto", dto);	
				session.setAttribute("Mem_num", dto.getMem_num());

				forward.setPath("./Main.mm");
				forward.setRedirect(true);
				System.out.println(" 카카오 아이디는 @@@@@@@@@@@@@ : " + Mem_id);
				
				return forward;
				
			}
			
	}
	
}