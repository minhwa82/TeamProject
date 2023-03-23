
package project.movie.notice.db;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private int No_num;
	private String No_title;
	private String No_content;
	private String No_date;
	private String Mem_id;
}
