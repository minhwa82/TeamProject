package project.movie.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.movie.qna.db.QnaDAO;
import project.movie.qna.db.QnaDTO;

public class QnaContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaContentAction_execute() ");
		
		// 전달정보 저장
		int Mem_num = Integer.parseInt(request.getParameter("Mem_num"));
		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = new QnaDAO();
		
		QnaDTO dto = dao.getQna(Mem_num);
		
		String tmp = dto.getMem_content().replace("\r\n", "<br>");
		
		dto.setMem_content(tmp);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./qna/content.jsp");
		forward.setRedirect(false);
			
		return forward;
	}

}
