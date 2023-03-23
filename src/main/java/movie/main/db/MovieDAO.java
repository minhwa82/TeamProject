package movie.main.db;

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

import booking.db.BookingDTO;
import member.db.MemberDTO;


public class MovieDAO {
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
		
	public void insertMovie(MovieDTO dto) {
		String date=dto.getM_playDate();
		String M_num = null;
		try {
			con = getCon();
			
			
			sql = "select count(*) from movie where M_playDate=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				M_num = date+(rs.getInt(1)+1);
			}
			sql = "insert into movie(M_num, M_name, M_type, M_runtime, M_director, "
					+ " M_age, M_actor, M_img, M_playDate, M_explain, "
					+ " M_grade, M_reservationRate ) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, M_num);
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_type());
			pstmt.setString(4, dto.getM_runTime());
			pstmt.setString(5, dto.getM_director());
			pstmt.setInt(6, dto.getM_age());
			pstmt.setString(7, dto.getM_actor());
			pstmt.setString(8, dto.getM_img());
			pstmt.setString(9, dto.getM_playDate());
			pstmt.setString(10, dto.getM_explain());
			pstmt.setDouble(11, dto.getM_grade());
			pstmt.setDouble(12, dto.getM_reservationRate());
			
			pstmt.executeUpdate();
			System.out.println("DAO : 영화등록완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
	}

	public List<MovieDTO> MList() {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		
		try {
			con = getCon();
			sql = "select * from movie order by M_reservationRate desc";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				
				dto.setM_actor(rs.getString("M_actor"));
				dto.setM_age(rs.getInt("M_age"));
				dto.setM_director(rs.getString("M_director"));
				dto.setM_explain(rs.getString("M_explain"));
				dto.setM_grade(rs.getDouble("M_grade"));
				dto.setM_img(rs.getString("M_img"));
				dto.setM_name(rs.getString("M_name"));
				dto.setM_num(rs.getString("M_num"));
				dto.setM_playDate(rs.getString("M_playDate"));
				dto.setM_reservationRate(rs.getDouble("M_reservationRate"));
				dto.setM_runTime(rs.getString("M_runTime"));
				dto.setM_type(rs.getString("M_type"));
				
				movieList.add(dto);
			}
			System.out.println(" DAO : 조회 성공! ");
			System.out.println(" DAO : "+movieList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return movieList;
	}

	// 영화등록
	
	// 영화조회
	
	public MovieDTO getMovieList(String M_num)
		{
			MovieDTO dto = null;
			try {
				con = getCon();
				sql ="select * from movie where M_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, M_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto = new MovieDTO();
					
					dto.setM_actor(rs.getString("M_actor"));
					dto.setM_age(rs.getInt("M_age"));
					dto.setM_director(rs.getString("M_director"));
					dto.setM_explain(rs.getString("M_explain"));
					dto.setM_grade(rs.getDouble("M_grade"));
					dto.setM_img(rs.getString("M_img"));
					dto.setM_name(rs.getString("M_name"));
					dto.setM_num(rs.getString("M_num"));
					dto.setM_playDate(rs.getString("M_playDate"));
					dto.setM_reservationRate(rs.getDouble("M_reservationRate"));
					dto.setM_runTime(rs.getString("M_runTime"));
					dto.setM_type(rs.getString("M_type"));
				}
				System.out.println("DAO : 영화 조회 ");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return dto;
		}

	
	// 영화 조회 -

	// 상품삭제

	public void deleteMovie(String M_num) {
		try {
			con = getCon();
			sql = "delete from movie where M_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, M_num);
			
			pstmt.executeUpdate();
		
			System.out.println(" DAO : 상품정보 삭제완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// 상품삭제
	
	// 회원정보 조회
	
			public List<MemberDTO> getMemberList(){
				List<MemberDTO> memList = new ArrayList<MemberDTO>();
				 
				
				try {
					con = getCon();
					sql = "select * from member ";
					pstmt = con.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						MemberDTO dto = new MemberDTO();
						
						dto.setMem_num(rs.getInt("Mem_num"));
						dto.setMem_name(rs.getString("Mem_name"));
						dto.setMem_id(rs.getString("Mem_id"));
						dto.setMem_pw(rs.getString("Mem_pw"));
						dto.setMem_phone(rs.getString("Mem_phone"));
						dto.setMem_birth(rs.getString("Mem_birth"));
						dto.setMem_grade(rs.getString("Mem_grade"));
						dto.setMem_addr1(rs.getString("Mem_addr1"));
						dto.setMem_email(rs.getString("Mem_email"));
						dto.setMem_mType(rs.getString("Mem_mType"));
						dto.setMem_joinDate(rs.getTimestamp("Mem_joinDate"));
						dto.setRCount(rs.getInt("rCount"));
						
						memList.add(dto);
						
					}
						System.out.println("회원 정보 조회");
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				
				return memList;
			}
			
			//회원 조회
			public MemberDTO getMemberList(int Mem_num)
			{
				MemberDTO dto = null;
				try {
					con = getCon();
					sql ="select * from member where Mem_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Mem_num);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						dto = new MemberDTO();
						
						dto.setMem_addr1(rs.getString("Mem_addr1"));
						dto.setMem_birth(rs.getString("Mem_birth"));
						dto.setMem_email(rs.getString("Mem_email"));
						dto.setMem_grade(rs.getString("Mem_grade"));
						dto.setMem_id(rs.getString("Mem_id"));
						dto.setMem_joinDate(rs.getTimestamp("Mem_joinDate"));
						dto.setMem_mType(rs.getString("Mem_mType"));
						dto.setMem_name(rs.getString("Mem_name"));
						dto.setMem_num(rs.getInt("Mem_num"));
						dto.setMem_phone(rs.getString("Mem_phone"));
						dto.setMem_pw(rs.getNString("Mem_pw"));
						dto.setRCount(rs.getInt("Mem_rCount"));
						
						
					}
					System.out.println("회원 조회");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeDB();
				}
				
				return dto;
			}

			//회원 조회
			
			// 영화 정보 수정 - updateMovie(dto)
			
			public void updateMovie(MovieDTO dto) {
				try {
					// 1,2 디비 연결
					con = getCon();
					// 3. sql작성(update)
					sql="update movie set "
							+ " M_name=?,M_type=?,M_runTime=?,M_director=?,M_age=?,M_actor=?,M_img=?, "
							+ " M_playDate=?,M_explain=?,M_grade=?,M_reservationRate=? "
							+ " where M_num=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getM_name());
					pstmt.setString(2, dto.getM_type());
					pstmt.setString(3, dto.getM_runTime());
					pstmt.setString(4, dto.getM_director());
					pstmt.setInt(5, dto.getM_age());
					pstmt.setString(6, dto.getM_actor());
					pstmt.setString(7, dto.getM_img());
					pstmt.setString(8, dto.getM_playDate());
					pstmt.setString(9, dto.getM_explain());
					pstmt.setDouble(10, dto.getM_grade());
					pstmt.setDouble(11, dto.getM_reservationRate());
					pstmt.setString(12, dto.getM_num());
					
					// 4. sql실행
					pstmt.executeUpdate();
					
					System.out.println(" dao : 영화정보 수정");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeDB();
				}
				
			}
				
			// 영화 정보 수정 - updateMovie(dto)

			// 영화 예매 목록 = getBookingList(
			
			public List<BookingDTO> getBookingList(){
				List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
				
				try {
					con = getCon();
					sql="select * from booking";
					pstmt = con.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					while(rs.next()) {
						BookingDTO dto = new BookingDTO();
						dto.setAdult_num(rs.getInt("Adult_num"));
						dto.setB_booking_num(rs.getString("B_booking_num"));
						dto.setB_cancel(rs.getString("B_cancel"));
						dto.setB_dateTime(rs.getTimestamp("B_dateTime"));
						dto.setB_num(rs.getString("B_num"));
						dto.setB_payInfo(rs.getString("B_payInfo"));
						dto.setB_payment(rs.getString("B_payment"));
						dto.setM_num(rs.getString("M_num"));
						dto.setMem_num(rs.getInt("Mem_num"));
						dto.setSc_num(rs.getString("Sc_num"));
						dto.setT_num(rs.getString("T_num"));
						dto.setTotal_price(rs.getInt("Total_price"));
						dto.setYouth_num(rs.getInt("Youth_num"));
						dto.setS_num(rs.getString("S_num"));
						bookingList.add(dto);
					}
							System.out.println("예매목록");
							
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bookingList;
			}

			// 영화 예매 목록 = getBookingList(
}
