package movie.movie.db;


import java.util.ArrayList;

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
	private String M_reservationRate;
	private String M_rank;
	
	
	public ArrayList toArray() {
		ArrayList list = new ArrayList<>();
		
		list.add(M_name);
		
		return list;
	}
	
	public String getM_rank() {
		return M_rank;
	}
	public void setM_rank(String m_rank) {
		M_rank = m_rank;
	}
	
	public String getM_num() {
		return M_num;
	}
	public void setM_num(String m_num) {
		M_num = m_num;
	}
	public String getM_name() {
		return M_name;
	}
	public void setM_name(String m_name) {
		M_name = m_name;
	}
	public String getM_type() {
		return M_type;
	}
	public void setM_type(String m_type) {
		M_type = m_type;
	}
	public String getM_runTime() {
		return M_runTime;
	}
	public void setM_runTime(String m_runTime) {
		M_runTime = m_runTime;
	}
	public String getM_director() {
		return M_director;
	}
	public void setM_director(String m_director) {
		M_director = m_director;
	}
	public int getM_age() {
		return M_age;
	}
	public void setM_age(int m_age) {
		M_age = m_age;
	}
	public String getM_actor() {
		return M_actor;
	}
	public void setM_actor(String m_actor) {
		M_actor = m_actor;
	}
	public String getM_img() {
		return M_img;
	}
	public void setM_img(String m_img) {
		M_img = m_img;
	}
	public String getM_playDate() {
		return M_playDate;
	}
	public void setM_playDate(String m_playDate) {
		M_playDate = m_playDate;
	}
	public String getM_explain() {
		return M_explain;
	}
	public void setM_explain(String m_explain) {
		M_explain = m_explain;
	}
	public double getM_grade() {
		return M_grade;
	}
	public void setM_grade(double m_grade) {
		M_grade = m_grade;
	}
	public String getM_reservationRate() {
		return M_reservationRate;
	}
	public void setM_reservationRate(String m_reservationRate) {
		M_reservationRate = m_reservationRate;
	}
	
	

}
