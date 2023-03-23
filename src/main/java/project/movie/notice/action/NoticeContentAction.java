package project.movie.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.notice.db.NoticeDAO;
import project.movie.notice.db.NoticeDTO;

public class NoticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : NoticeContentAction_execute() ");
	
		// 전달정보 저장
		int No_num = Integer.parseInt(request.getParameter("No_num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		
		
		NoticeDTO dto = dao.getNotice(No_num);
		
		String tmp = dto.getNo_content().replace("\r\n", "<br>");
		
		dto.setNo_content(tmp);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/content.jsp");
		forward.setRedirect(false);
		
			
		return forward;
	}

}
