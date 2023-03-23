package project.movie.screen.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.screen.db.ScreenDAO;

public class ScreenAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ScreenAction_execute() 호출 ");

		String Sc_num = request.getParameter("Sc_num");
		ScreenDAO dao = new ScreenDAO();


		
		request.setAttribute("dto", dao.screendetail(Sc_num));
//		request.setAttribute("Sc_num", Sc_num);
		ActionForward forward = new ActionForward();
		
		forward.setPath("./screen/screen.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
	

}
