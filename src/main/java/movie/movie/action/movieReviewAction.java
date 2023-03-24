package movie.movie.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;
import member.db.MemberDTO;
import movie.review.db.ReviewDAO;
import movie.review.db.ReviewDTO;


public class movieReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" moiveReviewAction_execute() 실행 ");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String M_num = request.getParameter("M_num");
		String Review = request.getParameter("Review");
		int grade = Integer.parseInt(request.getParameter("grade"));
		
		ReviewDTO dto = new ReviewDTO();
		ReviewDAO dao = new ReviewDAO();
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		mdto = mdao.memberInfo(id);
		
		dto.setMem_num(mdto.getMem_num());
		dto.setM_num(M_num);
		dto.setReview(Review);
		dto.setM_grade(grade);
		
		int result = dao.movieReviewInsert(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result==1) {
			out.write("<script>");
			out.write("alert('리뷰 등록 완료!');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
		}
		if(result == 0) {
			out.write("<script>");
			out.write("alert('이미 등록하셨습니다');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
		}
		return null;
	}

}
