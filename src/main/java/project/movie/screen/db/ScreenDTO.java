package project.movie.screen.db;

import lombok.Data;

@Data
public class ScreenDTO {
	private String Sc_num;
	private String Sc_zone;
	private String Sc_name;
	private String Sc_addr;
	private int Sc_price;
	private String Sc_img;
	private String Sc_phonenumber;
	private double Sc_latitude;
	private double Sc_longitude;
}
