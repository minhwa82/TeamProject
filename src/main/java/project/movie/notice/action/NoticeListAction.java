package project.movie.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import project.movie.notice.db.NoticeDAO;



public class NoticeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : NoticeListAction_execute() 호출 ");

		// NoticeDAO 객체 생성
		NoticeDAO dao = new NoticeDAO();
		// 게시판 전체글 개수
		int cnt = dao.getNoticeCount();

		// 페이징 처리 1 - DB에서 원하는 만큼의 글을 가져오기/////////////
		// 한페이지에 출력할 글의 개수
		int pageSize = 5;

		// 현페이지가 몇페이지 인가 판단
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		List noticeList = dao.getNoticeListPage(startRow, pageSize);

		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

		// 한 화면에 출력할 페이지 블럭의 개수
		int pageBlock = 3;

		// 페이지블럭 시작번호
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		// 페이지블럭 끝번호
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

	     // 전달할 정보를 request 영역에 저장(글정보 + 페이징처리정보)
	      request.setAttribute("noticeList", noticeList);
	      request.setAttribute("pageNum", pageNum);
	      request.setAttribute("cnt", cnt);
	      request.setAttribute("pageCount", pageCount);
	      request.setAttribute("pageBlock", pageBlock);
	      request.setAttribute("startPage", startPage);
	      request.setAttribute("endPage", endPage);
	
	      // 페이지 이동
	      ActionForward forward = new ActionForward();
	      forward.setPath("./notice/noticeList.jsp");
	      forward.setRedirect(false);
	      return forward;
   }

}
