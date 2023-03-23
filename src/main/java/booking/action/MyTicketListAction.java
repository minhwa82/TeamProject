package booking.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.action.ActionForward;

import booking.db.BookingDAO;

public class MyTicketListAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MyTicketListAction_execute() 호출 ");
		
		BookingDAO dao = new BookingDAO();
		int cnt = dao.getTicketCount();
		
		System.out.println(" 총 예매 횟수 : " + cnt);
		
		/////////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리 1 - DB에서 원하는만큼 글 가져오기
		// 한 페이지에 출력할 글의 개수
		int pageSize = 2; 
		
		// 현재 페이지가 몇 페이지인가 판단
		// 페이지 번호가 없을때 내가 보는 페이지가 첫 페이지도록 if문 설정
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		// 페이지 시작 행 번호 계산
		// 1 11 21 31 41 ....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// 페이지 끝 행 번호 계산
		// 10 20 30 40 ....
		int endRow = currentPage * pageSize;
		//////////////////////////////////////////////////////////////////////////////////////
		
		// 게시판 글 가져오기
		List boardList = dao.getTicketList(startRow, pageSize);
	
		//////////////////////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리 2 - 페이지 하단부 페이지 이동 링크
			
		// 전체 필요한 페이지 수
		// ex) 글 120개, 페이지당 10개씩 -> 페이지 수 12개 
		// 	   글 125개, 페이지당 10개씩 -> 페이지 수 13개
		int pageCount = cnt / pageSize + (cnt%pageSize==0 ? 0:1);
			
		// 한 화면에 출력할 페이지 블럭의 개수
		int pageBlock = 3;
			
		// 페이지 블럭 시작번호
		// ex) 1~10 => 1
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1; 
			
		// 페이지 블럭 끝번호
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) {
				endPage = pageCount;
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////
		
		// 전달할 정보를 request 영역에 저장 (글 정보 + 페이징 처리 정보)
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
	
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false); // forwarding 방식
		return forward;	
		
		return null;
	}

}
