package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.EmailDAO;

public class SendEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SendEmailAction_execute() 호출 ");
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); // 삭제예정
		
		String mem_email = request.getParameter("mem_email");
		
		EmailDAO emailDAO = new EmailDAO();
		String randomCode = emailDAO.randomCode();
		emailDAO.SendEmail(mem_email,randomCode);
		
		response.getWriter().write(randomCode+"");
		request.setAttribute("randomCode", randomCode);
		System.out.println("인증코드 : "+randomCode);
		

		return null;
	}

}
