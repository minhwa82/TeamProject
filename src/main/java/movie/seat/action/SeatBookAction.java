package movie.seat.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;
import project.movie.booking.db.BookingDAO;
import project.movie.screen.db.ScreenDAO;
import project.movie.screen.db.ScreenDTO;

public class SeatBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SeatBookAction_execute() 실행 ");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String[] S_num = request.getParameterValues("seat");
		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String Sc_num = request.getParameter("Sc_num");
		String M_name = request.getParameter("M_name");
		String M_num = request.getParameter("M_num");
		
		System.out.println(T_num);
		System.out.println(Sc_num);
		
		int aNum;
		int yNum;
		
		if(request.getParameter("aNum") == null || request.getParameter("aNum") == "") {
			aNum = 0;
		}else {
			aNum = Integer.parseInt(request.getParameter("aNum"));
		}
		
		if(request.getParameter("yNum") == null || request.getParameter("yNum") == "") {
			yNum = 0;
		}
		else {
			yNum = Integer.parseInt(request.getParameter("yNum"));
		}
		
		System.out.println("a : "+ aNum);
		System.out.println("y : "+ yNum);
		
		ScreenDAO sdao = new ScreenDAO();
		ScreenDTO sdto = new ScreenDTO();
		sdto = sdao.screendetail(Sc_num);
		String Sc_name = sdto.getSc_zone() + sdto.getSc_name();
		int price = sdto.getSc_price();
		int tPrice = (int) ((aNum * price) + (yNum * price));
		
		BookingDAO bdao = new BookingDAO();
		int booking_num = bdao.makeBookNum();
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = now.format(formatter);
		
		String Book_num = date + booking_num;
		
		MemberDTO mdto = new MemberDTO();
		MemberDAO mdao = new MemberDAO();
		mdto = mdao.memberInfo(id);
		String Mem_name = mdto.getMem_name();
		String Mem_grade = mdto.getMem_grade();
		int Mem_num = mdto.getMem_num();
		
		request.setAttribute("Mem_num", Mem_num);
		request.setAttribute("Mem_grade", Mem_grade);
		request.setAttribute("Mem_name", Mem_name);
		request.setAttribute("Book_num", Book_num);
		request.setAttribute("M_name", M_name);
		request.setAttribute("M_num", M_num);
		request.setAttribute("Sc_name", Sc_name);
		request.setAttribute("S_num", S_num);
		request.setAttribute("T_num", T_num);
		request.setAttribute("Sc_num", Sc_num);
		request.setAttribute("aNum", aNum);
		request.setAttribute("yNum", yNum);
		request.setAttribute("tPrice", tPrice);
		request.setAttribute("price", price);

		
		ActionForward forward = new ActionForward();
		forward.setPath("./seat/seatPay.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
