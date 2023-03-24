package movie.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.db.MovieDAO;
import movie.seat.db.SeatDAO;
import movie.seat.db.SeatDTO;
import project.movie.booking.db.BookingDAO;

public class BookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" BookAction_excute() 실행 ");
		
		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String Sc_num = request.getParameter("Sc_num");
		String M_num = request.getParameter("M_num");
		String[] S_num = request.getParameterValues("S_num");
		int aNum = Integer.parseInt(request.getParameter("aNum"));
		int yNum = Integer.parseInt(request.getParameter("yNum"));
		int tPrice = Integer.parseInt(request.getParameter("tPrice"));
		String Book_num = request.getParameter("Book_num");
		int Mem_num = Integer.parseInt(request.getParameter("Mem_num"));
		
		System.out.println(T_num);
		System.out.println(Sc_num);
		System.out.println(M_num);
		System.out.println(S_num);
		System.out.println(aNum);
		System.out.println(yNum);
		System.out.println(tPrice);
		
		SeatDTO dto = new SeatDTO();
		SeatDAO dao = new SeatDAO();

		BookingDAO bdao = new BookingDAO();
		// 멤버 번호 추가
		bdao.booking(T_num, Sc_num, M_num, S_num, aNum, yNum, tPrice, Book_num, Mem_num);
		
		dto.setT_num(T_num);
		dto.setSc_num(Sc_num);
		
		for(int i=0; i<S_num.length; i++) {
			dto.setS_num(S_num[i]);
			System.out.println(S_num[i]);
			dao.seatBook(dto);
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/MyTicket.me");
		forward.setRedirect(false);
		
		return forward;
	}

}
