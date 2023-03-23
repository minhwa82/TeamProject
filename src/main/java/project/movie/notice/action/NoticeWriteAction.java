package project.movie.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.notice.db.NoticeDAO;
import project.movie.notice.db.NoticeDTO;

public class NoticeWriteAction implements Action {
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		System.out.println(" M : NoticeWriteAction_execute() 호출 ");
		
		// 한글처리
		//request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보 저장(파라미터)
		NoticeDTO dto = new NoticeDTO();
		dto.setMem_id(request.getParameter("Mem_id"));
		dto.setNo_content(request.getParameter("No_content"));
		dto.setNo_title(request.getParameter("No_title"));
		// BoardDAO - 글쓰기()
		NoticeDAO dao = new NoticeDAO();
		dao.insertNotice(dto);
		
		// 페이지이동 (list)
		ActionForward forward = new ActionForward();
		forward.setPath("./NoticeListAction.no");
		forward.setRedirect(true);	
		
		return forward;
	}

}
