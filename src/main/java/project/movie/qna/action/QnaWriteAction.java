package project.movie.qna.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import project.movie.qna.db.QnaDAO;
import project.movie.qna.db.QnaDTO;


public class QnaWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

			System.out.println(" M : QnaWriteAction_execute() 호출 ");
			
			// 한글처리
			//request.setCharacterEncoding("UTF-8");
			
			// 전달된 정보 저장(파라미터)
			QnaDTO dto = new QnaDTO();
	//		dto.setMem_id(request.getParameter("Mem_id"));
			dto.setMem_content(request.getParameter("Mem_content"));
			dto.setMem_subject(request.getParameter("Mem_subject"));

			// BoardDAO - 글쓰기()
			QnaDAO dao = new QnaDAO();
			dao.insertQna(dto);
			
			// 페이지이동 (list)
			ActionForward forward = new ActionForward();
			forward.setPath("./QnaListAction.qa");
			forward.setRedirect(true);	
			
		return forward;
	}
	

}