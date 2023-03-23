package movie.movie.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.mv")
public class MovieFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doProcess 호출 ! ");
		
	////////////////////////1. 가상주소 계산 /////////////////////////////////
	System.out.println("\n\n 1. 가상주소 계산 - 시작 ");
	String requestURI = request.getRequestURI();
	System.out.println(" C : requestURI - " + requestURI);
	String ctxPath = request.getContextPath();
	System.out.println(" C : ctxPath - " + ctxPath);
	String command = requestURI.substring(ctxPath.length());
	System.out.println(" C : command - " + command);
	System.out.println("\n 1. 가상주소 계산 - 끝 ");
	//////////////////////// 1. 가상주소 계산 /////////////////////////////////
	
	
	//////////////////////// 2. 가상주소 매핑 /////////////////////////////////
	System.out.println("\n\n 2. 가상주소 매핑 - 시작 ");
	Action action = null;
	ActionForward forward = null;
	
	if(command.equals("/main.mv")) {
		System.out.println(" /main.mv 호출 ");
		
		forward = new ActionForward();
		forward.setPath("./movie/main.jsp");
		forward.setRedirect(false);
		
	}
	
	else if(command.equals("/movieDetail.mv")) {
		System.out.println(" /movieDetail.mv 호출 ");
		
		action = new MovieDetailAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	else if(command.equals("/movieList.mv")) {
		System.out.println(" /movieList.mv 호출 ");
		
		action = new MovieListAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	else if(command.equals("/saveDB.mv")) {
		System.out.println(" /saveDB.mv 호출 ");
		
		forward = new ActionForward();
		
		forward.setPath("./movie/dbSave.jsp");
		forward.setRedirect(false);
		
	}
	
	
	else if(command.equals("/saveDBProAction.mv")) {
		System.out.println(" /saveDBProAction.mv 호출 ");
		
		action = new SaveDBProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	else if(command.equals("/movieReview.mv")) {
		System.out.println(" /movieReview.mv 호출 ");
		
		action = new movieReviewAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	else if(command.equals("/timeInsert.mv")) {
		System.out.println(" /timeInsert.mv 호출 ");
		
		action = new TImeInsertAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	else if(command.equals("/timeInsertProAction.mv")) {
		System.out.println(" /timeInsertProAction.mv 호출 ");
		
		action = new TimeInsertProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(command.equals("/bookAction.mv")) {
		System.out.println(" /bookAction.mv 호출 ");
		
		action = new BookAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	System.out.println("\n 2. 가상주소 매핑 - 끝 ");
	//////////////////////// 2. 가상주소 매핑 /////////////////////////////////
	
	
	//////////////////////// 3. 가상주소 이동 /////////////////////////////////
	System.out.println("\n\n 3. 가상주소 이동 - 시작 ");
	
	if(forward != null) { // 페이지 이동정보가 있을때
		if (forward.isRedirect()) { // true
			System.out.println(" C : 방식 -" + forward.isRedirect() + ", 주소 - " + forward.getPath());
			response.sendRedirect(forward.getPath());
		} else { // false
			System.out.println(" C : 방식 -" + forward.isRedirect() + ", 주소 - " + forward.getPath());
			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
		}
	}
	System.out.println("\n 3. 가상주소 이동 - 끝 ");
	//////////////////////// 3. 가상주소 이동 /////////////////////////////////
		
	}
	
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doGET 호출 ! ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doPOST 호출 ! ");
		doProcess(request, response);
	}

}
