package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;

public class NaverLoginAction implements Action{

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {	
	
		System.out.println(" M : NaverLoginAction_execute() 호출 ");
			
			MemberDTO dto = new MemberDTO();
			String id = request.getParameter("id");
			String Mem_id = "N_" + id.substring(0,8);
			String Mem_pw = "naver";
			
			MemberDAO dao = new MemberDAO();
			int result = dao.naverLogin(Mem_id, Mem_pw);
						
			ActionForward forward = new ActionForward();
			
			// 결과에 따른 페이지 이동
			if(result == -1) {
								
				System.out.println(" 비회원이다!!!!!!  ");
				dto.setMem_id(Mem_id);
				dto.setMem_pw(Mem_pw);
				dto.setMem_name(request.getParameter("name"));
				dto.setMem_email(request.getParameter("email"));
	//			dto.setMem_birth(request.getParameter("birthday"));  생일 컬럼 타입 오류 
				dto.setMem_phone(request.getParameter("mobile")); // 

				System.out.println(" M : DTO " + dto);
				
				// 네이버 회원가입 메서드 호출
				dao.naverJoin(dto);
				System.out.println(" M : 네이버 회원가입 성공!!!!!!!!!!!!!!!!!!! ");
				
				// 로그인
				dao.naverLogin(Mem_id, Mem_pw);

				HttpSession session = request.getSession();
				session.setAttribute("id", Mem_id);
				forward.setPath("./Main.mm");
				forward.setRedirect(true);		
							
				return forward;	
					
			}else { // result == 1 로그인성공 -> 아이디 정보 session 영역 저장	
				
				System.out.println(" M : 네이버 로그인 성공!!!!!!!!!!!!!!!!!!! ");
				HttpSession session = request.getSession();
				session.setAttribute("id", Mem_id);
				
				forward.setPath("./Main.mm");
				forward.setRedirect(true);
				System.out.println(" 네이버 아이디는 @@@@@@@@@@@@@ : " + Mem_id);
				
				return forward;
				
			}
		}
}
