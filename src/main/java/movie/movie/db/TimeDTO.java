package movie.movie.db;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class TimeDTO {
	private int T_num;
	private String Sc_num;
	private String M_num;
	private String T_startTime;
	private String T_endTime;
	private Date T_date;
	public int getT_num() {
		return T_num;
	}
	public void setT_num(int t_num) {
		T_num = t_num;
	}
	public String getSc_num() {
		return Sc_num;
	}
	public void setSc_num(String sc_num) {
		Sc_num = sc_num;
	}
	public String getM_num() {
		return M_num;
	}
	public void setM_num(String m_num) {
		M_num = m_num;
	}
	public String getT_startTime() {
		return T_startTime;
	}
	public void setT_startTime(String t_startTime) {
		T_startTime = t_startTime;
	}
	public String getT_endTime() {
		return T_endTime;
	}
	public void setT_endTime(String t_endTime) {
		T_endTime = t_endTime;
	}
	public Date getT_date() {
		return T_date;
	}
	public void setT_date(Date t_date) {
		T_date = t_date;
	}
	
	

}
