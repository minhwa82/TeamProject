package project.movie.qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.qna.db.QnaDAO;

public class QnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
     // 전달정보 저장
		int Q_num = Integer.parseInt(request.getParameter("Q_num"));
		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = new QnaDAO();
		int result = dao.deleteQna(Q_num);
		
		// 삭제 처리 결과에 따른 페이지 이동(JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("<script>");
		out.write(" alert('삭제 성공!'); ");
		out.write(" location.href='./QnaListAction.qa?pageNum="+pageNum+"'; ");
		out.write("</script>");		
		out.close();
		
		
		return null;
	
	}

}
