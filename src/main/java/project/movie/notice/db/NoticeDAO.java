package project.movie.notice.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class NoticeDAO {
	
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
	  	DataSource ds
	  	      = (DataSource) initCTX.lookup("java:comp/env/jdbc/movie");
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

	
	// 글쓰기-insertNotice(dto)
		public void insertNotice(NoticeDTO dto) throws Exception {
			int No_num = 0;

			//1.2 디비연결
			
				con = getCon();
				
				// 3. SQL 작성(글번호 계산) & pstmt 객체
				String sql = "select max(No_num) from notice";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// 4. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				// 5. 데이터처리
				if (rs.next()) {
					
					No_num = rs.getInt(1) + 1;
				}

				System.out.println(" No_num : " + No_num);

				// 3. SQL 작성(insert) & pstmt 객체
				sql = "insert into notice (No_num,Mem_id,No_title,No_content,No_date) "
						+ "values(?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, No_num);
				pstmt.setString(2, dto.getMem_id());
				pstmt.setString(3, dto.getNo_title());
				pstmt.setString(4, dto.getNo_content());

				// 4. SQL 실행
				pstmt.executeUpdate();
				System.out.println(" DAO : 글쓰기 완료! ");
	
				rs.close();
				pstmt.close();
				con.close();
		}
		public List getNoticeListAll() {

			List noticeList = new ArrayList();

			// - 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비연결
			try {
				con = getCon();

				sql = "select * from notice";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					NoticeDTO dto = new NoticeDTO();

					dto.setNo_num(rs.getInt("No_num"));
					dto.setMem_id(rs.getString("Mem_id"));
					dto.setNo_title(rs.getString("No_title"));
					dto.setNo_content(rs.getString("No_content"));
					dto.setNo_date(rs.getString("No_date"));

					noticeList.add(dto);

				} // while

				System.out.println(" DAO : 게시판 글정보 모두 저장완료 ");

			} catch (Exception e) {
				e.printStackTrace();
			}

			return noticeList;
		} 
		
		public List getNoticeListPage(int startRow, int pageSize) {

			List noticeList = new ArrayList();
			
			try {
				con = getCon();
			
				sql = "select * from notice " 
				     + "order by No_num desc "
						+ "limit ? , ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow - 1);
				pstmt.setInt(2, pageSize);

				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				while (rs.next()) {
					NoticeDTO dto = new NoticeDTO();

					dto.setNo_num(rs.getInt("No_num"));
					dto.setMem_id(rs.getString("Mem_id"));
					dto.setNo_title(rs.getString("No_title"));
					dto.setNo_content(rs.getString("No_content"));
					dto.setNo_date(rs.getString("No_date"));
					
					noticeList.add(dto);

				} // while

				System.out.println(" DAO : 공지사항 글정보 모두 저장완료 ");

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
			return noticeList;
		}
		
		
		// 글 전체 개수 조회
		/**
		 * 글 전체 개수를 조회하는 메서드
		 * 
		 * @return 글 전체 개수
		 */
		public int getNoticeCount() {
			int cnt = 0;
			try {
				// 1.드라이버로드
				// 2.디비연결
				con = getCon();
				// 3. sql 작성 & pstmt 객체
				sql = "select count(*) from notice";
				pstmt = con.prepareStatement(sql);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터처리
				if (rs.next()) {
					cnt = rs.getInt(1);
				}
				System.out.println(" DAO : 글 전체 개수 :" + cnt);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}

			return cnt;
		}
		// 글 전체 개수 조회

	    // 특정 글정보를 가져오는 메서드  - getBoard(no_num)
		public NoticeDTO getNotice(int No_num) {
			NoticeDTO dto = null;
			
			
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 작성(select) & pstmt객체
				sql = "select * from notice "
						+ " where No_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, No_num);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터처리 (저장)
				if(rs.next()) {
					// DB데이터 -> BoardDTO 저장	
					dto = new NoticeDTO();
					
					dto.setNo_num(rs.getInt("No_num"));
					dto.setMem_id(rs.getString("Mem_id"));
					dto.setNo_title(rs.getString("No_title"));
					dto.setNo_content(rs.getString("No_content"));
					dto.setNo_date(rs.getString("No_date"));
							
				}
				
				System.out.println(" DAO : 글정보 저장완료! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return dto;
		}
		
		// 글 수정
		public void updateNotice(NoticeDTO dto) {
			
			try {
				con=getCon();
				sql="update notice set " 
				+ " Mem_id=?, "
				+ " No_content=?, No_title=? " 
				+ " where No_num=? ";
				pstmt=con.prepareStatement(sql);
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getMem_id());
				pstmt.setString(2, dto.getNo_content());
				pstmt.setString(3, dto.getNo_title());
				pstmt.setInt(4, dto.getNo_num());
				
				pstmt.executeUpdate();
				
			  System.out.println(" 수정 ooo");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
				
			}
		}
	   
		
		// 게시판 글삭제 deleteNotice(dto)
		public int deleteNotice(int No_num) {
			int result =0;
			
			try {
				con = getCon();
				
				sql="delete from notice where No_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, No_num);
				
				result=pstmt.executeUpdate();
				System.out.println(" DAO : 글 삭제 완료! ");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		}


		public List<NoticeDTO> searchNoticeList(String search,int startRow,int pageSize){
			
			List<NoticeDTO> searchList = new ArrayList<NoticeDTO>();
			
			try {
				//1.2. 디비연결
				con = getCon();
				// 3. sql 작성 & pstmt 객체
				//  검색결과를 조회(like), 정렬- re_ref 내림차순(최신글 위로)/re_seq 오름차순
				//  페이징처리 limit 사이즈만큼 처리
				
				sql = "select * from notice "
						+ " where No_title like ? ";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+search+"%");
//				pstmt.setInt(2, startRow-1);
//				pstmt.setInt(3, pageSize);
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터처리 
				while(rs.next()) {
					NoticeDTO dto = new NoticeDTO();
					dto.setNo_num(rs.getInt("No_num"));
					dto.setMem_id(rs.getString("Mem_id"));
					dto.setNo_title(rs.getString("No_title"));
					dto.setNo_content(rs.getString("No_content"));
					dto.setNo_date(rs.getString("No_date"));
					
					searchList.add(dto);
				
				}
				
				System.out.println(" DAO : 검색결과 저장완료! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return searchList;
			}
		// 일반 로그인 - loginMember(MemberDTO dto)
		public int loginMember(MemberDTO dto) {
				
			int result = -1;
				
			try {
				// DB 연결
				con = getCon();
					
				sql = "select Mem_pw from member where Mem_id=?"; // 비회원이다.
				PreparedStatement pstmt = con.prepareStatement(sql);
					
				pstmt.setString(1, dto.getMem_id());
					
				ResultSet rs = pstmt.executeQuery();
					
				// 5. 데이터처리
				if(rs.next()) {
					
					if(dto.getMem_pw().equals(rs.getString("Mem_pw"))) {
						//본인		
						result = 1;
					}else {
						// 비밀번호 오류
						result = 0;
					}			
				}else {
					// 비회원(아이디 정보없음)
					result = -1;
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;		
			
		} // loginMember
		
		
}
