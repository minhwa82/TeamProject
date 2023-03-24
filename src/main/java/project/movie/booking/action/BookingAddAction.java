package project.movie.booking.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.moive.booking.db.BookingDTO;

public class BookingAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BookingAddAction_execute() 호출 ");
		// 전달정보 저장
		BookingDTO dto = new BookingDTO();
//		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String startTime = request.getParameter("startTimeR");
//		String m_name = request.getParameter("M_name");
//		String sc_name = request.getParameter("Sc_name");
		String b_date = request.getParameter("movieDate");
		dto.setM_num(request.getParameter("movie_num"));
		dto.setSc_num(request.getParameter("screen_num"));
		request.setAttribute("dto", dto);
		request.setAttribute("b_date", b_date);
		request.setAttribute("startTime", startTime);
//		request.setAttribute("T_num", T_num);
		ActionForward forward = new ActionForward();
		forward.setPath("./seat/seatTest.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
