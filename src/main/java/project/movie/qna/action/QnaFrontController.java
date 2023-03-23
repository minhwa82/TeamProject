package project.movie.qna.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("*.qa")
public class QnaFrontController extends HttpServlet{
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
	    
	    if(command.equals("/QnaListAction.qa")) {
	    	System.out.println(" C : /QnaListAction.qa 호출");
	    	 //  패턴3 QnaListAction() 객체
	    	action = new QnaListAction();

	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    	else if(command.equals("/QnaWrite.qa")) {
	    		System.out.println(" C : /QnaWrite.qa 호출");	    		
	    		System.out.println(" C : DB 사용x, viewO");
	    		
	    		forward= new ActionForward();
	    		forward.setPath("./qna/qnaWrite.jsp");
	    		forward.setRedirect(false);    		
	    	}
	    	else if(command.equals("/QnaWriteAction.qa")) {
	    		System.out.println(" C : /QnaWriteAction.qa 호출 ");
	    		System.out.println(" C : DB사용o, view페이지 이동");
	    		
	    		action= new QnaWriteAction();
	    		
	    		try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}	
	    	}
	    	else if(command.equals("/QnaContentAction.qa")) {
	    		System.out.println(" C : /QnaContentAction.qa 호출 ");
	    		System.out.println(" C : DB사용O, view출력");
	    		
	    		action= new QnaContentAction();
	    		
	    		try {
					forward= action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    	else if(command.equals("/QnaUpdateAction.qa")) {
	    		System.out.println(" C : /QnaUpdateAction.qa 호출");
	    		
	    		action = new QnaUpdateAction();
	    		
	    		try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    	else if(command.equals("/QnaUpdateActionPro.qa")){
	    		System.out.println(" C : /QnaUpdateActionPro.qa 호출");
	    		
	    		action= new QnaUpdateActionPro();
	    		
	    		try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
	    	}
	    	else if(command.equals("/QnaDeleteAction.qa")) {
	    		System.out.println(" C : /QnaDeleteAction.qa 호출");
	    		
	    		action = new QnaDeleteAction();
	    		
	    		try {
					forward= action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doGET 호출! ");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doPOST 호출! ");
		doProcess(request, response);
	}

}
