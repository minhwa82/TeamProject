package project.movie.screen.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ScreenDAO {
	// 공통 변수 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 드라이버로드 + 디비연결 => 커넥션풀

		// JNDI(Java Naming and Directory Interface) API 사용
		// -> 특정 자원(파일/데이터)에 접근하기위한 기술을 제공한다
		// Context 객체 생성
		Context initCTX = new InitialContext();
		// context.xml 파일에 있는 리소스 정보를 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/movie");
		// 디비연결
		con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공! " + con);

		return con;
	}
	// 디비연결 메서드

	// 자원해제
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

	// 극장 정보 조회
	public ScreenDTO screendetail(String Sc_num) {
//		List screenList = new ArrayList();
		ScreenDTO dto = new ScreenDTO();
		try {
			con = getCon();

			sql = "select * from screen where Sc_num=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Sc_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setSc_addr(rs.getString("Sc_addr"));
				dto.setSc_name(rs.getString("Sc_name"));
				dto.setSc_num(rs.getString("Sc_num"));
				dto.setSc_price(rs.getInt("Sc_price"));
				dto.setSc_img(rs.getString("Sc_img"));
				dto.setSc_phonenumber(rs.getString("Sc_phonenumber"));
				dto.setSc_latitude(rs.getDouble("Sc_latitude"));
				dto.setSc_longitude(rs.getDouble("Sc_longitude"));
//				screenList.add(dto);
			}

			System.out.println(" DAO : 극장 정보 저장완료 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		} 

		return dto;
	}
	// 극장 정보 조회
	
	// 극장 목록 출력
	public List screenList() {
		List screenList = new ArrayList();
		
		try {
			con = getCon();
			
			sql = "select * from screen";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScreenDTO dto = new ScreenDTO();
				dto.setSc_zone(rs.getString("Sc_zone"));
				dto.setSc_name(rs.getString("Sc_name"));
				
				screenList.add(dto);
			}
			System.out.println(" DAO : 스크린 목록 저장 완료 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return screenList;
	}
	
	// 극장 목록 출력
}
