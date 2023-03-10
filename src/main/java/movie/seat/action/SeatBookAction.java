package movie.seat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeatBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SeatBookAction_execute() 실행 ");
		
		String S_num = request.getParameter("seat");
		System.out.println(S_num);
		
		return null;
	}

}
