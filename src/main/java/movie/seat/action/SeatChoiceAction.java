package movie.seat.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import movie.seat.db.SeatDAO;
import movie.seat.db.SeatDTO;

public class SeatChoiceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SeatChoiceAction_execute() 실행 ");
		
		String M_num = request.getParameter("M_num");
		String M_name = request.getParameter("M_name");
		
		// T_num, Sc_num 받기
		// 받기전 임시
		
		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String Sc_num = request.getParameter("Sc_num");
		System.out.println(Sc_num);
		System.out.println(T_num);
		System.out.println(M_num);
		System.out.println(M_name);
		
		SeatDTO dto = new SeatDTO();
		SeatDAO dao = new SeatDAO();
		
		List SList = dao.getSeatStatus(Sc_num, T_num, M_num);
		
		System.out.println("SList:"+SList);
		request.setAttribute("SList", SList);
		request.setAttribute("T_num", T_num);
		request.setAttribute("Sc_num", Sc_num);
		request.setAttribute("M_name", M_name);
		
		// JSOnLISt
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonList="";
//
//		try {
//			jsonList = mapper.writeValueAsString(SList);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(jsonList);
//		request.setAttribute("JList", jsonList);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./seat/seat.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
