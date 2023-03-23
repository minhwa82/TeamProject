package project.movie.qna.db;

import java.sql.Date;

import lombok.Data;

@Data
public class QnaDTO {
	
	private int Q_num;
	private String Mem_subject;
	private String Mem_content;
	private Date Q_date;
	private String Mem_id;
	
}