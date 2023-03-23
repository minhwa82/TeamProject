package booking.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booking.db.BookingDTO;
import booking.db.BookingDAO;

public class MyTicketAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MyTicketAction_execute 호출 ");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println(" 아이디 : " + id);
			
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;			
		}
		
		// Mem_num 정보 가져오기
		int Mem_num = Integer.parseInt(request.getParameter("Mem_num"));
		//int B_num = Integer.parseInt(request.getParameter("B_num"));

		BookingDAO dao = new BookingDAO();
		List ticketList = dao.ticketInfo(Mem_num);
		
		System.out.println(" M : " + ticketList);
		
		request.setAttribute("ticketList", ticketList);		
		
		forward.setPath("./booking/Ticket.jsp");
		forward.setRedirect(false);
		
		return forward ;

	}

}
