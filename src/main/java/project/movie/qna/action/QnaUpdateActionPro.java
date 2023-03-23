package project.movie.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.qna.db.QnaDAO;
import project.movie.qna.db.QnaDTO;

public class QnaUpdateActionPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String pageNum = request.getParameter("pageNum");
		
		QnaDTO dto = new QnaDTO();
		dto.setQ_num(Integer.parseInt(request.getParameter("Q_num")));
		dto.setMem_id(request.getParameter("Mem_id"));
		dto.setMem_content(request.getParameter("Mem_content"));
		dto.setMem_subject(request.getParameter("Mem_subject"));
		
		QnaDAO dao = new QnaDAO();
		dao.updateQna(dto);
		
		System.out.println(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./QnaListAction.qa");
		forward.setRedirect(true);
		
		return forward;
	}

}
