package movie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.LoginAction;

// Member컨트롤러: 회원정보 관련된 처리만 수행(서블릿)
@WebServlet("*.mm")
public class MovieFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doProcess() 호출 ");

//		System.out.println(request.getRequestURI());
//		System.out.println(request.getRequestURL());
		/////////////////////// 1. 가상주소 계산 //////////////////////////
		System.out.println("\n\n 1. 가상주소 계산 - 시작 ");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println("\n 1. 가상주소 계산 - 끝 ");
		
		/////////////////////// 1. 가상주소 계산 //////////////////////////

		/////////////////////// 2. 가상주소 매핑 //////////////////////////
		System.out.println("\n\n 2. 가상주소 매핑 - 시작 ");
		Action action = null;
		ActionForward forward = null;
		 
		 if(command.equals("/Main.mm")) {
			// 패턴 3
			action = new MovieMainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 }
		else if(command.equals("/AdminMovieList.mm")) {
			System.out.println("C : AdminMovieList.mv 호출");
			//패턴 3
			action = new AdminMovieListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/Bonus.mm")) {
			System.out.println("C : Bonus.mv 호출");
			action = new BonusAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieWrite.mm")) {
			System.out.println("C : MovieWrite.mv 호출");
			action = new MovieWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieAddAction.mm")) {
			System.out.println("C : MovieAddAction.mv 호출");
			
			action = new MovieAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// 로그인
		else if (command.equals("/Login.mm")) {
			System.out.println(" C : /Login.mv 호출! ");
			
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./movie/loginForm.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/LoginAction.mm")) {
			System.out.println(" C : /LoginAction.mv 호출! ");
			System.out.println(" C : DB 동작 필요, view 이동  ");
			
			// 패턴2
			action = (Action) new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		else if(command.equals("/MovieUpdateAction.mm")) {
			System.out.println(" C : MovieUpdateAction 호출");
			
			action = new MovieUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MovieDelteAction.mm")) {
			System.out.println(" C : MovieDeleteAction 호출");
			action = new MovieDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminPage.mm")) {
			System.out.println("/AdminPage.mv 호출");
			action = new AdminPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/AdminMemberInfo.mm")) {
			System.out.println("/AdminMemberInfo.mv  호출");
			action = new AdminMemberInfo();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdMemInfoUpdate.mm ")) {
			System.out.println("AdMemInfoUpdate.mv 호출");
			
			action = new AdMemInfoUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminMovieUpdate.mm")) {
			System.out.println("AdminMovieUpdate.mv 호출");
			action = new AdminMovieUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/AdminBookingList.mm")) {
			System.out.println("AdminBookingList.mv 호출");
			action = new AdminBookingList();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		 
		 
		 
		
		System.out.println("\n 2. 가상주소 매핑 - 끝 ");
		/////////////////////// 2. 가상주소 매핑 //////////////////////////

		/////////////////////// 3. 가상주소 이동 //////////////////////////
		System.out.println("\n\n 3. 가상주소 이동 - 시작 ");
		
		if(forward != null) {  // 이동정보가 있을때
			if(forward.isRedirect()) { // true
				System.out.println(" C : 방식 - "+forward.isRedirect()+", 주소 - "+forward.getPath());
				response.sendRedirect(forward.getPath());					
			}else {//false
				System.out.println(" C : 방식 - "+forward.isRedirect()+", 주소 - "+forward.getPath());
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		System.out.println("\n 3. 가상주소 이동 - 끝 ");
		/////////////////////// 3. 가상주소 이동 //////////////////////////

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doGET() 호출 ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doPOST() 호출 ");
		doProcess(request, response);
	}

}
