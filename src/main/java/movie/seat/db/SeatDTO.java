package movie.seat.db;

public class SeatDTO {
	private String S_num;
	private String Sc_num;
	private int S_choice;
	private String M_num;
	private String startTime;
	private String endTime;
	private String date;
	
	
	
	public String getM_num() {
		return M_num;
	}
	public void setM_num(String m_num) {
		M_num = m_num;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getS_num() {
		return S_num;
	}
	public void setS_num(String s_num) {
		S_num = s_num;
	}
	public String getSc_num() {
		return Sc_num;
	}
	public void setSc_num(String sc_num) {
		Sc_num = sc_num;
	}
	public int getS_choice() {
		return S_choice;
	}
	public void setS_choice(int s_choice) {
		S_choice = s_choice;
	}
	
	

}
