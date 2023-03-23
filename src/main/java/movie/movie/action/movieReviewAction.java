package movie.movie.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.review.db.ReviewDAO;
import movie.review.db.ReviewDTO;


public class movieReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" moiveReviewAction_execute() 실행 ");
		
		ReviewDTO dto = new ReviewDTO();
		ReviewDAO dao = new ReviewDAO();
		
		dto.setMem_num(1);
		dto.setM_num("");
		dto.setReview(null);
		dto.setM_grade(0);
		
		dao.movieReviewInsert(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("<script>");
		out.write("alert('리뷰 등록 완료!');");
		out.write("history.back();");
		out.write("</script>");
		out.close();
		
		return null;
	}

}
