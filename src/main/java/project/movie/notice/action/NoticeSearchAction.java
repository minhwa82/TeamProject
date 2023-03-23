package project.movie.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.notice.db.NoticeDAO;
import project.movie.notice.db.NoticeDTO;

public class NoticeSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : NoticeSearchAction_execute 호출");
		
		// 전달정보 저장
		String search = request.getParameter("search");
		System.out.println(" M : search : "+search);
		
		NoticeDAO dao = new NoticeDAO();
		
		int cnt = dao.getNoticeCount();
		
		int pageSize=5;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow =(currentPage-1)*pageSize+1;
		int endRow = currentPage *pageSize;
		
		List<NoticeDTO> searchList = dao.searchNoticeList(search,startRow,pageSize);
		
		int pageCount =cnt /pageSize + (cnt % pageSize ==0 ? 0:1);
		
		int pageBlock =3;
		
		int startPage=((currentPage-1) /pageBlock)*pageBlock+1;
		
		int endPage = startPage + pageBlock -1;
		if(endPage>pageCount) {
			endPage =pageCount;
		}
		
		request.setAttribute("noticeList", searchList);
		System.out.println(searchList);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/noticeList.jsp");
		forward.setRedirect(false);
			
		return forward;
	}

}
