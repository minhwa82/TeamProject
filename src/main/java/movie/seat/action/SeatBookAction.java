package movie.seat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.seat.db.SeatDAO;
import movie.seat.db.SeatDTO;

public class SeatBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SeatBookAction_execute() 실행 ");
		
		String[] S_num = request.getParameterValues("seat");
		
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
		
		SeatDTO dto = new SeatDTO();
		SeatDAO dao = new SeatDAO();
		
		// 받기전 임시
		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String Sc_num = request.getParameter("Sc_num");
		
		System.out.println(T_num);
		System.out.println(Sc_num);
		
		dto.setT_num(T_num);
		dto.setSc_num(Sc_num);
		
		
		for(int i=0; i<S_num.length; i++) {
			dto.setS_num(S_num[i]);
			System.out.println(S_num[i]);
			dao.seatBook(dto);
		}
		
		
		
		// 예매 페이지로 보내기
	
		
		
		return null;
	}

}
