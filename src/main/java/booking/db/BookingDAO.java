package booking.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.db.MemberDTO;
import movie.main.db.MovieDTO;
import movie.movie.db.TimeDTO;
import project.movie.screen.db.ScreenDTO;

public class BookingDAO {
	
	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
		
	// 공통 기능 (메서드) 구현
	// 1. DB 연결 - 커넥션풀
	private Connection getCon() throws Exception{
			
		// Context 객체 (Project) 생성
		Context initCTX = new InitialContext(); // 업캐스팅
			
		// DB 정보 연동, context.xml의 name과 동일
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/movie"); 
		con = ds.getConnection();		
		return con;
	}
		
	// 2. 자원 해제
	public void closeDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
	// 예매 정보 출력 - ticketInfo(int Mem_num)
	public List ticketInfo(int Mem_num) {
		
		List subList = new ArrayList();
		List totalList = new ArrayList();

		BookingDTO bdto = null;
		MovieDTO mvdto = null;
		ScreenDTO scdto = null;
		TimeDTO tdto = null;
		
		System.out.println(Mem_num);
		      
		try {
		    	
			con = getCon();
		//	sql = "select * from booking where Mem_num=?";
		//  booking, movie, screen, time 테이블 JOIN 
			sql = "select b.B_booking_num, m.M_name, t.T_startTime, s.Sc_name, b.S_num, b.Youth_num, b.Adult_num "
					+ "from booking b "
					+ "left join movie m on b.M_num = m.M_num "
					+ "left join screen s on b.Sc_num = s.Sc_num "
					+ "left join time t on b.T_num = t.T_num "
					+ "where b.Mem_num=? "
					+ "group by B_booking_num, m.M_name, t.T_num,s.Sc_name, b.S_num, b.Youth_num, b.Adult_num";
		         
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Mem_num);
			rs = pstmt.executeQuery();
		         
			if (rs.next()) {
				
				// 마이페이지에 출력할 예매 정보 담기
				bdto = new BookingDTO();     
				mvdto = new MovieDTO();
				scdto = new ScreenDTO();
				tdto = new TimeDTO();
				
				bdto.setB_booking_num(rs.getString("B_booking_num"));
				bdto.setS_num(rs.getString("S_num"));
				bdto.setYouth_num(rs.getInt("Youth_num"));
		        bdto.setAdult_num(rs.getInt("Adult_num"));
	      
		        mvdto.setM_name(rs.getString("M_name"));
		        scdto.setSc_name(rs.getString("Sc_name"));
		        tdto.setT_startTime(rs.getString("T_startTime"));
		        
		        subList.add(bdto);
		        subList.add(mvdto);
		        subList.add(scdto);
		        subList.add(tdto);
		        
		        totalList.add(subList);
		        
			}
			
			System.out.println(" @@@@@@@@@ " + totalList);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
		
		return totalList;
		      
	}

	public int getTicketCount() {
		
		int cnt = 0;
		
		try {
			con = getCon();
			
			sql = "select count(*) from booking where Mem_num=?";
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
			System.out.println(" DAO : 총 예매 횟수 " + cnt);
							
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return cnt;
	} 
	
	
	
}


