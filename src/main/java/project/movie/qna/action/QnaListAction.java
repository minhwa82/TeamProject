package project.movie.qna.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.movie.qna.db.QnaDAO;
import project.movie.qna.db.QnaDTO;

public class QnaListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
	 System.out.println(" M : QnaListAction_execute() 호출 ");
		 
	  QnaDAO dao = new QnaDAO();
			// 게시판 전체글 개수
		int cnt = dao.getQnaCount();

		// 페이징 처리 1 - DB에서 원하는 만큼의 글을 가져오기/////////////
				// 한페이지에 출력할 글의 개수
				int pageSize = 5;

				// 현페이지가 몇페이지 인가 판단
				String pageNum = request.getParameter("pageNum");
				if (pageNum == null) {
					pageNum = "1";
				}
				// 페이지 시작행 번호 계산
				// 1 11 21 31 41 .....
				int currentPage = Integer.parseInt(pageNum);
				int startRow = (currentPage - 1) * pageSize + 1;
				// 페이지 끝행 번호 계산
				// 10 20 30 40....
				int endRow = currentPage * pageSize;
				//////////////////////////////////////////////////////////////////

				// 게시판 글 가져오기
				List qnaList = dao.getQnaListPage(startRow, pageSize);

				///////////////////////////////////////////////////////////
				// 페이징 처리 2 - 페이지 하단부 페지이 이동링크
				// 전체 필요한 페이지 수
				// ex) 글 : 120 , 페이지당 10개씩 -> 페이지수 : 12
				// 글 : 127 , 페이지당 10개씩 -> 페이지수 : 13

				int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

				// 한 화면에 출력할 페이지 블럭의 개수
				int pageBlock = 3;

				// 페이지블럭 시작번호
				// ex) 1~10 => 1, 11~20 => 11, 21~30 => 21
				int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
				// 페이지블럭 끝번호
				int endPage = startPage + pageBlock - 1;
				if (endPage > pageCount) {
					endPage = pageCount;
				}
				
				
				
		///////////////////////////////////////////////////////////

//		      전달할 정보를 request 영역에 저장(글정보 + 페이징처리정보)
		      request.setAttribute("qnaList", qnaList);
	
		      request.setAttribute("pageNum", pageNum);
		      request.setAttribute("cnt", cnt);
		      request.setAttribute("pageCount", pageCount);
		      request.setAttribute("pageBlock", pageBlock);
		      request.setAttribute("startPage", startPage);
		      request.setAttribute("endPage", endPage);
		      
		   //   QnaDTO dto = new QnaDTO();
//중복?	//  List qnaList = dao.getQnaList();
	        //  request.setAttribute("qnaList", qnaList);
		   //   System.out.println(qnaList);
	      
			ActionForward forward = new ActionForward();
			forward.setPath("./qna/qnaList.jsp");
			forward.setRedirect(false);
		
			return forward;
	}

}
