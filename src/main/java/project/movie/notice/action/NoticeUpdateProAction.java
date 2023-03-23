package project.movie.notice.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.notice.db.NoticeDAO;
import project.movie.notice.db.NoticeDTO;

public class NoticeUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : NoticeUpdateProAction_execute 호출 ");
		
		String pageNum = request.getParameter("pageNum");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNo_title(request.getParameter("No_title"));
		dto.setNo_num(Integer.parseInt(request.getParameter("No_num")));
		dto.setMem_id(request.getParameter("Mem_id"));
		dto.setNo_content(request.getParameter("No_content"));
		System.out.println(dto);
		
		NoticeDAO dao = new NoticeDAO();
		dao.updateNotice(dto);
		
//		int result = dao.updateNotice(dto);
//		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		System.out.println(" M : JS 사용페이지 이동(컨트롤러 이동x)");
		
		ActionForward forward = new ActionForward();
		forward.setPath("./NoticeListAction.no?");
		forward.setRedirect(true);
		return forward;
	
	}

}
