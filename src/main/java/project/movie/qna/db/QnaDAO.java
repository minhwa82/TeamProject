package project.movie.qna.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.movie.notice.db.NoticeDTO;



public class QnaDAO {
	
	// 공통 변수 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		
		DataSource ds
	  	      = (DataSource) initCTX.lookup("java:comp/env/jdbc/movie");
	  	
	  	con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공! " + con);

		return con;
	}
	

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

	
	// 글쓰기
		public void insertQna(QnaDTO dto) throws Exception {
			int Q_num = 0;

			//1.2 디비연결
			con = getCon();

			// 3. SQL 작성(글번호 계산) & pstmt 객체
			String sql = "select max(Q_num) from qna";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 4. SQL 실행
			ResultSet rs = pstmt.executeQuery();
			
			// 5. 데이터처리
			if (rs.next()) {
				
				Q_num = rs.getInt(1) + 1;
			}

			System.out.println(" Q_num : " + Q_num);

			// 3. SQL 작성(insert) & pstmt 객체
			sql = "insert into qna (Q_num,Mem_id,Mem_subject,Mem_content,Q_date)"
					+ "values(?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);

			
			pstmt.setInt(1, Q_num);
			pstmt.setString(2, dto.getMem_id());
			pstmt.setString(3, dto.getMem_subject());
			pstmt.setString(4, dto.getMem_content());
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 글쓰기 완료!");
			rs.close();
			pstmt.close();
			con.close();
			
		}
		
		public List getQnaListAll() {

			List qnaList = new ArrayList();

			try {
				con = getCon();
				// 3. sql 작성 & pstmt 객체
				sql = "select * from qna";
				pstmt = con.prepareStatement(sql);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				// rs(DB) -> InquDTO -> list
				while (rs.next()) {
					// rs -> DTO
					QnaDTO dto = new QnaDTO();

					dto.setQ_num(rs.getInt("Q_num"));
					dto.setMem_subject(rs.getString("Mem_subject"));
					dto.setMem_content(rs.getString("Mem_content"));
					dto.setQ_date(rs.getDate("Q_date"));
		
					qnaList.add(dto);

				} // while

				System.out.println(" DAO : 글정보 모두 저장완료 ");

			} catch (Exception e) {
				e.printStackTrace();
			}

			return qnaList;
		}
		
	public List getQnaListPage(int startRow, int pageSize) {

			List qnaList = new ArrayList();
			// - 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비연결
		try {
				con = getCon();
				// 3. sql 작성 & pstmt 객체
			
				sql = "select * from qna " 
				     + "order by Q_num desc "
						+ "limit ?,?";
				pstmt = con.prepareStatement(sql);
			
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				// rs(DB) -> InquDTO -> list
				while (rs.next()) {
					// rs -> DTO
					QnaDTO dto = new QnaDTO();

					dto.setQ_num(rs.getInt("Q_num"));
					dto.setMem_subject(rs.getString("Mem_subject"));
					dto.setMem_content(rs.getString("Mem_content"));
					dto.setQ_date(rs.getDate("Q_date"));
					dto.setMem_id(rs.getString("Mem_id"));

					qnaList.add(dto);

				} // while

				System.out.println(" DAO : 게시판 글정보 모두 저장완료 ");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 자원해제-DB연결정보 해제
				// 사용했던 자원의 역순 해제 (안전)
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

			return qnaList;
		}
 
	// 글전체 개수 조회
	/**
	 * 글 전체 개수를 조회하는 메서드
	 * @return 글 전체 개수
	 */
	
		public int getQnaCount() {
			int cnt =0;
			
			try {
				con=getCon();
			sql = "select count(*) from qna";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt(1);
			}
			System.out.println(" DAO : 글 전체 개수 :"+cnt);
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				closeDB();
			}
				
			return cnt;
		}
		
	// 특정 글 정보를 가져오는 메서드 getQna(Q_num)
		public QnaDTO getQna(int Q_num) {
			QnaDTO dto = null;
			
			try {
				// 1.2.디비연결
				con=getCon();
				sql ="select * from qna "
						+ " where Q_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Q_num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					dto = new QnaDTO();
					
					dto.setQ_num(rs.getInt("Q_num"));
					dto.setMem_subject(rs.getString("Mem_subject"));
					dto.setMem_content(rs.getString("Mem_content"));
					dto.setMem_id(rs.getString("Mem_id"));
					dto.setQ_date(rs.getDate("Q_date"));
				}
				System.out.println(" DAO : 글정보 저장완료!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return dto;
		}
		// updateQna(dto) - 글 수정
		
		public void updateQna(QnaDTO dto) {
			try {
				con=getCon();
				sql="update qna set "
						+ " Mem_subject=?, "
						+ " Mem_content=?, Mem_id=?"
						+ " where Q_num=? ";
						
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getMem_subject());
				pstmt.setString(2, dto.getMem_content());
				pstmt.setString(3, dto.getMem_id());
				pstmt.setInt(4, dto.getQ_num());
				
				pstmt.executeUpdate();
				
				System.out.println("수정 ㅇㅇㅇㅇ");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		
		 	
		public List getQnaList() {

			List qnaList3 = new ArrayList();

			// - 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비연결
			try {
				con = getCon();
				// 3. sql 작성 & pstmt 객체
				sql = "select * from qna";
				pstmt = con.prepareStatement(sql);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				// rs(DB) -> NoticeDTO -> list
				while (rs.next()) {
					// rs -> DTO
					QnaDTO dto = new QnaDTO();

					dto.setQ_num(rs.getInt("Q_num"));
					dto.setMem_subject(rs.getString("Mem_subject"));
					dto.setMem_content(rs.getString("Mem_content"));
					dto.setQ_date(rs.getDate("Q_date"));
					dto.setMem_id(rs.getString("Mem_id"));
				

					// dto -> list
					qnaList3.add(dto);

				} // while

					System.out.println(" DAO : 게시판 글정보 모두 저장완료 ");

				} catch (Exception e) {
					e.printStackTrace();
				}

				return qnaList3;
			}


		public int deleteQna(int Q_num) {
			int result =0;
			
			try {
				con = getCon();
				
				sql="delete from qna where Q_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Q_num);
				
				result=pstmt.executeUpdate();
				System.out.println(" DAO : 글 삭제 완료! ");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		}


		
			
			
}

	