package project.moive.booking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import movie.movie.db.MovieDTO;

public class BookingDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private Connection getCon() throws Exception {
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
	
	public int makeBookNum() {
		int B_num = 1;
		try {
			con = getCon();
			sql= "select max(B_num) from booking";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				B_num = rs.getInt(1) + 1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return B_num;
	}
	
	
	public void booking(int T_num, String Sc_num, String M_num, String[] S_num, int aNum, int yNum, int tPrice, String Book_num) {
		int B_num = 1;
		try {
			con = getCon();
			sql = "select max(B_num) from booking";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				B_num = rs.getInt(1) + 1;
				
				sql = "insert into booking values(?,?,?,?,?,3,?,?,?,?,now(),?,0)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, B_num);
				pstmt.setString(2, Book_num);
				pstmt.setInt(3, T_num);
				pstmt.setString(4, Sc_num);
				pstmt.setString(5, M_num);
				if(S_num.length>1) {
					pstmt.setString(6, S_num[0]+"외 "+ (S_num.length - 1));
				}else {
					pstmt.setString(6, S_num[0]);
				}
				pstmt.setInt(7, tPrice);
				pstmt.setInt(8, yNum);
				pstmt.setInt(9, aNum);
				pstmt.setString(10, "dd");
				
				
				pstmt.executeUpdate();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	


}
