package project.movie.booking.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.moive.booking.db.BookingDAO;

public class ScreenChoiceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ScreenChoiceAction_execute() 호출 ");
		String M_num = request.getParameter("M_num");
		String Sc_num = request.getParameter("Sc_num");
		BookingDAO dao = new BookingDAO();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
//		Time startTime = movieDAO.getStartTime(M_num);
		List dateList = dao.getMovieDate(M_num, Sc_num);
//		for (int i = 0; i < timeList.size(); i++) {
//			out.write(((TimeDTO)timeList.get(i)).getT_startTime() + "");
//			System.out.println(" startTime : " + ((TimeDTO)timeList.get(i)).getT_startTime()); 
//			System.out.println(" sc_num : "+((TimeDTO) timeList.get(i)).getSc_num());
//		}
//		out.write(timeList)
		System.out.println("M_num : " + M_num);
		System.out.println("Sc_num:" + Sc_num);
		System.out.println(dateList);
		request.setAttribute("dateList", dateList);
		ActionForward forward = new ActionForward();
		forward.setPath("./booking/movieDate.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
