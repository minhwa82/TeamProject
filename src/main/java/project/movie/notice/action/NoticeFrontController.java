package project.movie.notice.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.no")
public class NoticeFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doProcess 호출! ");
		
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
		
		
		if(command.equals("/NoticeWrite.no")) {
			System.out.println(" C : /NoticeWrite.no 호출 ");
			System.out.println(" C : DB 사용 x, view O ");
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./notice/noticeWrite.jsp");
			forward.setRedirect(false);		
		}
		else if(command.equals("/NoticeWriteAction.no")) {
			System.out.println(" C : /NoticeWriteAction.no 호출 ");
			System.out.println(" C : DB사용 O, view 페이지 이동 ");
			// 패턴2
			// BoardWriteAction() 객체 생성
			action = new NoticeWriteAction();
		
		try {
		forward= action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		else if(command.equals("/NoticeListAction.no")) {
			System.out.println(" C : /NoticeListAction.no호출 ");
			System.out.println(" C : DB사용 O, view O");
			
			// 패턴3
			// BoardListAction() 객체
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
			else if(command.equals("/NoticeContentAction.no")) {
				System.out.println(" C : /NoticeContentAction.no 호출 ");
				System.out.println(" C : DB사용 O, view 페이지 이동");
				
				action= new NoticeContentAction();
				try {
				forward = action.execute(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}			
				
			}

		else if(command.equals("/NoticeSearchAction.no")) {
			System.out.println(" C : /NoticeSearchAction.no 호출");
			
			action= new NoticeSearchAction();
			
			try {
			forward = action.execute(request, response);
			} catch (Exception e) {	
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeDeleteAction.no")) {
			System.out.println(" C : /NoticeDeleteAction.no 호출");
			
			action= new NoticeDeleteAction();
			
			try {
			forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeUpdateAction.no")) {
			System.out.println(" C : /NoticeUpdateAction.no 호출");
			
			action= new NoticeUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeUpdateProAction.no")) {
			System.out.println("/NoticeUpdateProAction.no 호출");
			action = new NoticeUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else if(command.equals("/AdminPage.no")) {
			action = new AdminPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Login.no")) {
			forward = new ActionForward();
			forward.setPath("./notice/loginForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/LoginAction.no")) {
			System.out.println("/LoginAction 호출");
			action = new LoginAction();
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doGET 호출! ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doPOST 호출! ");
		doProcess(request, response);
	}
	
	

}
