package movie.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {
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
	
	
	// movieReviewInsert(int Mem_name, String M_num)
	public int movieReviewInsert(ReviewDTO dto) {
		int result = 0;
		try {
			con = getCon();
			sql = "insert into review (Mem_num, M_num, review, M_grade, review_date) "
					+ "values(?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getMem_num());
			pstmt.setString(2, dto.getM_num());
			pstmt.setString(3, dto.getReview());
			pstmt.setInt(4, dto.getM_grade());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // movieReviewInsert(int Mem_name, String M_num)
	
	
	
	// getMovieReview(String M_num)
	public List getMovieReview(String M_num) {
		List reviewList = new ArrayList<>();
		
		try {
			con = getCon();
			sql = "select * from review where M_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, M_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setMem_num(rs.getInt(1));
				dto.setM_num(rs.getString(2));
				dto.setReview(rs.getString(3));
				dto.setM_grade(rs.getInt(4));
				dto.setReview_date(rs.getDate(5));
				
				reviewList.add(dto);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		
		
		return reviewList;
	}
	
	
	// getMovieReview(String M_num)
	
	
	
	

}





























