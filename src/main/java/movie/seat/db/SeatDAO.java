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
	
	public void insertSeat(String S_num, String Sc_num, int T_num) {
		
		try {
			con = getCon();
			sql = "insert into seat values(?,?,0,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, S_num);
			pstmt.setString(2, Sc_num);
			pstmt.setInt(3, T_num);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	
	
	public List getSeatStatus(String Sc_num, int T_num, String M_num) {
		List SList = new ArrayList<>();
		SeatDTO dto = null;
		
		try {
			con = getCon();
			sql = "select S_num, S_choice, M_num from seat join time "
					+ "on seat.T_num = time.T_num "
					+ "where seat.Sc_num=? and seat.T_num=? and time.M_num=? "
					+ "order by S_num";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, Sc_num);
			pstmt.setInt(2, T_num);
			pstmt.setString(3, M_num);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new SeatDTO();
				
				dto.setS_num(rs.getString(1));
				dto.setS_choice(rs.getInt(2));
				dto.setM_num(rs.getString(3));
				
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
	
	
	// seatBook(SeatDTO dto)
	public void seatBook(SeatDTO dto) {
		try {
			con = getCon();
			sql = "update seat set S_choice=1 where S_num=? and Sc_num=? and T_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getS_num());
			pstmt.setString(2, dto.getSc_num());
			pstmt.setInt(3, dto.getT_num());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	
	// seatBook(SeatDTO dto)
	
	
	

}




































