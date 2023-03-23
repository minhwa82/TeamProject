package project.movie.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.qna.db.QnaDAO;
import project.movie.qna.db.QnaDTO;

public class QnaUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : QnaUpdateAction_execute() ");
		
		int Q_num = Integer.parseInt(request.getParameter("Q_num"));
		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = new QnaDAO();
		QnaDTO dto = new QnaDTO();
		 request.setAttribute("dto", dao.getQna(Q_num));
		request.setAttribute("pageNum", pageNum);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./qna/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
