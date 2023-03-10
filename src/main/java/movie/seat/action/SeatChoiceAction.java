package movie.seat.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.seat.db.SeatDAO;
import movie.seat.db.SeatDTO;

public class SeatChoiceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SeatChoiceAction_execute() 실행 ");
		
		String M_num = request.getParameter("M_num");
		String M_name = request.getParameter("M_mame");
		
		// T_num, Sc_num 받기
		// 받기전 임시
		
		String T_num = "1";
		String SC_num = "02_1";
		
		SeatDAO dao = new SeatDAO();
		SeatDTO dto = new SeatDTO();
		
		List SList = dao.getSeatStatus(SC_num, T_num);
		
		System.out.println(SList);
		request.setAttribute("SList", SList);
		
		// JSOnLISt
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./seat/seat.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
