package movie.movie.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" /CheckLoginAction_execute() 실행 ");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		PrintWriter out = response.getWriter();
		
		if(id==null) {
			out.print("X");
			out.close();
		}
		return null;
	}

}
