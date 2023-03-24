package member.action;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;
import movie.main.db.MovieDTO;
import project.moive.booking.db.BookingDAO;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println(" M : MyPageAction_execute() 호출 ");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int Mem_num = (int) session.getAttribute("Mem_num");
		
		ActionForward forward = new ActionForward();
		
		if(id == null) { // 로그인 안 됐을때
			forward.setPath("./Login.mm");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto =  dao.memberInfo(id);

		BookingDAO bdao = new BookingDAO();
		
		List totalList = bdao.ticketInfo(Mem_num);

		System.out.println(" @@@@@@@@@@@@@@@@@@@@@@ " + totalList);
		
		request.setAttribute("totalList", totalList);
		request.setAttribute("dto", dto);

		forward.setPath("./member/myPage.jsp");
		forward.setRedirect(false); 
		
		return forward;

	}

}
