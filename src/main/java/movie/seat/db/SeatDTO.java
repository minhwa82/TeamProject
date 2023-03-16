package movie.seat.db;

public class SeatDTO {
	private String S_num;
	private String Sc_num;
	private int S_choice;
	private int T_num;
	private String M_num;
	
	
	
	
	
	public String getM_num() {
		return M_num;
	}
	public void setM_num(String m_num) {
		M_num = m_num;
	}
	public int getT_num() {
		return T_num;
	}
	public void setT_num(int t_num) {
		T_num = t_num;
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
	@Override
	public String toString() {
		return "SeatDTO [S_num=" + S_num + ", Sc_num=" + Sc_num + ", S_choice=" + S_choice + ", T_num=" + T_num + "]";
	}
	
	
	
	

}
