package movie.seat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SeatDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	
	private Connection getCon() throws Exception{
		Context initCTX = new InitialContext();
		
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/movie");
		
		con = ds.getConnection();
		System.out.println(" DAO : DB연결");
		
		return con;
	}
	
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertSeat(String S_num, String Sc_num) {
		
		try {
			con = getCon();
			sql = "insert into seat values(?,?,0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, S_num);
			pstmt.setString(2, Sc_num);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	
	
	public List getSeatStatus(String M_num, String startTime, String Sc_num, String date) {
		List SList = new ArrayList<>();
		SeatDTO dto = null;
		
		try {
			con = getCon();
			sql = "select S_num, S_choice from seat where M_num=? and startTime=? and Sc_num=? and date=? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, M_num);
			pstmt.setString(2, startTime);
			pstmt.setString(3, Sc_num);
			pstmt.setString(4, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new SeatDTO();
				
				dto.setS_num(rs.getString(0));
				dto.setS_choice(rs.getInt(1));
				
				SList.add(dto);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		
		return SList;
	}
	
	

}




































