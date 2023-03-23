package movie.main.db;



import java.sql.Date;

import lombok.Data;

@Data
public class MovieDTO {
	private String M_num;
	private String M_name;
	private String M_type;
	private String M_runTime;
	private String M_director;
	private int M_age;
	private String M_actor;
	private String M_img;
	private String M_playDate;
	private String M_explain;
	private double M_grade;
	private double M_reservationRate;
	
	
}
