package movie.review.db;

import java.sql.Date;

import lombok.Data;

@Data
public class ReviewDTO {
	private int Mem_num;
	private String M_num;
	private String review;
	private int M_grade;
	private Date review_date;
	public int getMem_num() {
		return Mem_num;
	}
	public void setMem_num(int mem_num) {
		Mem_num = mem_num;
	}
	public String getM_num() {
		return M_num;
	}
	public void setM_num(String m_num) {
		M_num = m_num;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getM_grade() {
		return M_grade;
	}
	public void setM_grade(int m_grade) {
		M_grade = m_grade;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	@Override
	public String toString() {
		return "ReviewDTO [Mem_num=" + Mem_num + ", M_num=" + M_num + ", review=" + review + ", M_grade=" + M_grade
				+ ", review_date=" + review_date + "]";
	}
	
	
}
