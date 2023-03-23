package project.movie.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.notice.db.NoticeDAO;
import project.movie.notice.db.NoticeDTO;

public class NoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : NoticeUpdateAction_execute() 호출");
		
		int No_num = Integer.parseInt(request.getParameter("No_num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = new NoticeDTO();
		request.setAttribute("dto", dao.getNotice(No_num));
		request.setAttribute("pageNum", pageNum);
				
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
