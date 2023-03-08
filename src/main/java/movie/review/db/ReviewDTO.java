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
}
