package project.moive.booking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import movie.movie.db.MovieDTO;
import movie.movie.db.TimeDTO;
import project.movie.screen.db.ScreenDTO;

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

	public BookingDTO booking(String M_num, String Sc_num, int T_num) {
		BookingDTO dto = null;
		int b_num = 0; // 일련번호
		int b_booking_num = 0; // 예약번호

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			con = getCon();

			// 영화 정보 조회
			sql = "select * from movie where M_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, M_num);
			rs = pstmt.executeQuery();

			// 데이터 처리
			if (rs.next()) {
				sql = "select max(B_num) from booking";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					b_booking_num = rs.getInt(1) + 1;
				}
				System.out.println(" DAO : b_date " + b_booking_num);
				b_booking_num = b_num;
				System.out.println(" DAO : b_date " + b_num);

				sql = "insert into booking values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, b_num);
				pstmt.setString(2, cal.getTime() + "-" + b_booking_num);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public List getScreen(String M_num) {
		List getScList = new ArrayList();

		try {
			con = getCon();
			sql = "select distinct sc.sc_num, sc.sc_name "
					+ "	from screen sc join time t"
					+ "	on sc.sc_num = t.sc_num "
					+ "	where t.m_num=? and t.T_date >= curdate()";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, M_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScreenDTO dto = new ScreenDTO();
				dto.setSc_num(rs.getString("Sc_num"));
				dto.setSc_name(rs.getString("Sc_name"));
				getScList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getScList;
	}

	public List getMovieDate(String M_num, String Sc_num) {
		List tList = new ArrayList<>(); 
		TimeDTO dto;

		try {
			con = getCon();
			sql = "select T_num, Sc_num, M_num, T_startTime, T_endTime, T_date from time where M_num=? and Sc_num=? and T_date >= curdate()";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, M_num);
			pstmt.setString(2, Sc_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new TimeDTO();
				dto.setT_num(rs.getInt("T_num"));
				dto.setSc_num(rs.getString("Sc_num"));
				dto.setM_num(rs.getString("M_num"));
				dto.setT_startTime(rs.getString("T_startTime"));
				dto.setT_endTime(rs.getString("T_endTime"));
				dto.setT_date(rs.getDate("T_date"));

				tList.add(dto);
			}
			System.out.println(" DAO : getTime 저장 완료 " + tList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return tList;
	}

	public List getTime(String M_num, String Sc_num, String T_date) {
		List tList = new ArrayList<>();
		TimeDTO dto;

		try {
			con = getCon();
			sql = "select T_num, Sc_num, M_num, T_startTime, T_endTime, T_date from time "
					+ " where M_num=? and Sc_num=? and T_date=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, M_num);
			pstmt.setString(2, Sc_num);
			pstmt.setString(3, T_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new TimeDTO();
				dto.setT_num(rs.getInt("T_num"));
				dto.setSc_num(rs.getString("Sc_num"));
				dto.setM_num(rs.getString("M_num"));
				dto.setT_startTime(rs.getString("T_startTime"));
				dto.setT_endTime(rs.getString("T_endTime"));
				dto.setT_date(rs.getDate("T_date"));

				tList.add(dto);
			}
			System.out.println(" DAO : getTime 저장 완료 " + tList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return tList;
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
			// sql = "select * from booking where Mem_num=?";
			// booking, movie, screen, time 테이블 JOIN
			sql = "select b.B_booking_num, m.M_name, t.T_startTime, s.Sc_name, b.S_num, b.Youth_num, b.Adult_num "
					+ "from booking b " + "left join movie m on b.M_num = m.M_num "
					+ "left join screen s on b.Sc_num = s.Sc_num " + "left join time t on b.T_num = t.T_num "
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

			if (rs.next()) {
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
