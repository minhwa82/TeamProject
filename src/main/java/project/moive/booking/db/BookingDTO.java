package project.moive.booking.db;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BookingDTO {
	
	private int B_num;
	private String B_booking_num;	
	private int T_num;
	private String Sc_num;
	private String M_num;
	private int Mem_num;
	private String S_num;
	private int Total_price;
	private int Youth_num;
	private int Adult_num;
	private Timestamp B_dateTime;
	private String B_payment;
	private String B_cancel;
	
}