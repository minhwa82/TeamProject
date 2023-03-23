package project.movie.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.movie.notice.db.NoticeDAO;

public class NoticeDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    System.out.println(" M : NoticeDeleteAction_execute() 호출 ");
		
  
		// 전달정보 저장
		int No_num = Integer.parseInt(request.getParameter("No_num"));
		String pageNum = request.getParameter("pageNum");
		
		// BoardDAO - 삭제 메서드 오버로딩
		NoticeDAO dao = new NoticeDAO();
		int result = dao.deleteNotice(No_num);
		
		// 삭제 처리 결과에 따른 페이지 이동(JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("<script>");
		out.write(" alert('삭제 성공!'); ");
		out.write(" location.href='./NoticeListAction.no?pageNum="+pageNum+"'; ");
		out.write("</script>");		
		out.close();
		
		
		return null;
	}
	

}
