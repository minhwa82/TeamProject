package member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// ctrl + shift + o : 관계없는 import 패키지 자동 정리

public class MemberDAO {
	
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
			
	// 회원가입 - insertMember(dto)
		public void insertMember(MemberDTO dto) {
			try {
				// 1,2 디비연결
				con = getCon();
				// 3 sql 작성 & pstmt 객체

				sql = "insert into member(mem_name, mem_id, mem_pw, mem_phone, mem_birth, mem_postcode, mem_addr1, mem_addr2, mem_addr3, mem_email, mem_mType) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?);";
				pstmt = con.prepareStatement(sql);
				
				// ???
				pstmt.setString(1, dto.getMem_name());
				pstmt.setString(2, dto.getMem_id());
				pstmt.setString(3, dto.getMem_pw());
				pstmt.setString(4, dto.getMem_phone());
				pstmt.setString(5, dto.getMem_birth());
				pstmt.setString(6, dto.getMem_postcode());
				pstmt.setString(7, dto.getMem_addr1());
				pstmt.setString(8, dto.getMem_addr2());
				pstmt.setString(9, dto.getMem_addr3());
				pstmt.setString(10, dto.getMem_email());
				pstmt.setString(11, dto.getMem_mType());
				
				// 4 sql 실행		
				pstmt.executeUpdate();
				System.out.println(" DAO : 회원가입 성공! ");
				
				
			} catch (Exception e) {
				System.out.println(" DAO : 회원가입 실패! ");
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		
		
		//아이디,이메일,폰번호 중복검사
			public int check(String mem, String value) {
				
				//System.out.println(mem+","+value);
				
				//boolean check = false;
				int check = 0;
				try {
					//DBMS 연결 객체 가져오기
					con = getCon();
					//String으로 선언된 쿼리를 pstm객체에 전달하기
					sql = "SELECT count(Mem_id) FROM member WHERE "+mem+" = ?";
					pstmt = con.prepareStatement(sql);
					//SQL 쿼리에 ?가 있다면 알맞는 값으로 지정해주기
					pstmt.setString(1, value);
					//쿼리 실행 후 결과를 rs객체에 담기
					rs = pstmt.executeQuery();
					System.out.println(rs);
					//행가져오기
					rs.next();
					//위에서 가져온 행의 열을 타입에 맞춰서 가져오기
					//check = rs.getInt(1) == 1;	//0일 때 중복 없음(false), 1일 때 중복 있음(true)
					check = rs.getInt(1); // 1일때 중복있음
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				return check;
			}
		
		//
		
		// 회원정보 조회
		public MemberDTO getMemberInfo(String id){
			MemberDTO dto = null;
			// 디비 -> 회원정보 가져오기
			try {
				// 1. 드라이버 로드
				// 2. 디비연결
				con = getCon();
				// 3. SQL 작성(select) & pstmt 객체
				sql = "select Mem_id,Mem_pw, Mem_email, Mem_birth, Mem_phone, Mem_postcode, Mem_addr1, Mem_addr2, Mem_addr3, Mem_mType from member "
						+ " where Mem_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				// 화면에 출력X ->  출력정보 저장 (리턴) 
				if(rs.next()){
					dto = new MemberDTO();
					
					dto.setMem_id(rs.getString("Mem_id"));
					dto.setMem_pw(rs.getString("Mem_pw"));
					dto.setMem_email(rs.getString("Mem_email"));
					dto.setMem_birth(rs.getString("Mem_birth"));
					dto.setMem_phone(rs.getString("Mem_phone"));
					dto.setMem_postcode(rs.getString("Mem_postcode"));
					dto.setMem_addr1(rs.getString("Mem_addr1"));
					dto.setMem_addr2(rs.getString("Mem_addr2"));
					dto.setMem_addr3(rs.getString("Mem_addr3"));
					dto.setMem_mType(rs.getString("Mem_mType"));

				}
				
				System.out.println(" DAO : 회원정보 조회 성공! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return dto;
		}	
		// 회원정보 조회

		
		// 회원정보 수정 updateMember(dto)
					public int updateMember(MemberDTO dto) {
						int result = -1;
						try {
							//1.2 디비연결
							con = getCon();
							//3. sql 구문 & pstmt
							sql = "select Mem_id from member where Mem_id=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, dto.getMem_id());
							
							//4. sql 실행
							rs = pstmt.executeQuery();
							
							//5. 데이터처리 (수정)
							if(rs.next()) {
								
								if(dto.getMem_id().equals(rs.getString("Mem_id"))) {
									//3 sql (수정)
									sql = "update member set Mem_pw=?, Mem_email=?, Mem_birth=?, Mem_phone=?, Mem_postcode=?, Mem_addr1=?, Mem_addr2=?, Mem_addr3=?, Mem_mType=? where Mem_id=?";
									pstmt = con.prepareStatement(sql);
									
									pstmt.setString(1, dto.getMem_pw());
									pstmt.setString(2, dto.getMem_email());
									pstmt.setString(3, dto.getMem_birth());
									pstmt.setString(4, dto.getMem_phone());
									pstmt.setString(5, dto.getMem_postcode());
									pstmt.setString(6, dto.getMem_addr1());
									pstmt.setString(7, dto.getMem_addr2());
									pstmt.setString(8, dto.getMem_addr3());
									pstmt.setString(9, dto.getMem_mType());
									pstmt.setString(10, dto.getMem_id());
									
									result = pstmt.executeUpdate();
									
								}else {
									result = 0;
								}
								
							}else {
								result = -1;
							}
							
						} catch (Exception e) {
							e.printStackTrace();
					    } finally {
							closeDB();
						}
						
						
						return result;
					}	
					// 회원정보 수정 updateMember(dto)
			
	// 일반 로그인 - loginMember(MemberDTO dto)
	/* public int loginMember(MemberDTO dto) {
			
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
		
	} // loginMember */
	
	// 일반 로그인 - loginMember(MemberDTO dto)
	public int loginMember(MemberDTO dto) {
			
		int result = -1;
			
		try {
			// DB 연결
			con = getCon();
				
			sql = "select *  from member where Mem_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
				
			pstmt.setString(1, dto.getMem_id());
				
			rs = pstmt.executeQuery();
							
			// 5. 데이터처리
			if(rs.next()) {
				
				if(dto.getMem_pw().equals(rs.getString("Mem_pw"))) {
					//본인		
					result = 1;
					
					dto.setMem_num(rs.getInt("Mem_num"));
					dto.setMem_name(rs.getString("Mem_name"));
					dto.setMem_email(rs.getString("Mem_email"));
					dto.setRCount(rs.getInt("rCount"));
					
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
		
	// 네이버 회원가입 - naverJoin(MemberDTO dto)
	public void naverJoin(MemberDTO dto) {
		
		int Mem_num = 0;
		
		try {
			con = getCon();
			
			sql = "select max(Mem_num) from member";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Mem_num = rs.getInt(1)+1;
			}
			
			System.out.println(" DAO : Mem_num = " + Mem_num);
			
			sql = "insert into member(Mem_num,Mem_name,Mem_id,Mem_pw,Mem_phone,Mem_email) "
					+ "values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Mem_num);
			pstmt.setString(2, dto.getMem_name());
			pstmt.setString(3, dto.getMem_id());
			pstmt.setString(4, dto.getMem_pw());
			pstmt.setString(5, dto.getMem_phone());
			//pstmt.setDate(6, dto.getMem_birth());
			pstmt.setString(6, dto.getMem_email());
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 네이버 회원가입 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// naverJoin(DTO)
	
	// 네이버 로그인 - naverLogin(Mem_id, Mem_pw)
	public int naverLogin(String Mem_id, String Mem_pw) {
		
		int result = -1;
		
		try {

			con = getCon();

			sql = "select Mem_pw from member where Mem_id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Mem_id);

			rs = pstmt.executeQuery();
			
			// 5. 
			if(rs.next()) {
				
				// 회원일 경우
				if(Mem_pw.equals(rs.getString("mem_pw"))) {
					// 로그인 성공
					result = 1;
				}
			}else {
				// 비회원
				result = -1;
			}
			System.out.println(" DAO : 로그인 체크 = " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	} // naverLogin(Mem_id, Mem_pw)

	
	// 회원 탈퇴 - deleteMember (MemberDTO dto)
	public int deleteMember (MemberDTO dto) {
			
		// 0 : 비밀번호 오류 / 1 : 정상적으로 삭제 완료
		int result = -1;
			
		try {
			con = getCon();
				
			sql = "select Mem_pw from member where Mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMem_id());
			
			rs = pstmt.executeQuery();

			System.out.println(dto.getMem_pw());
					
			if(rs.next()) {

				// select 구문으로 가져온 비밀번호와 입력한 비밀번호가 동일한지 비교
				if(dto.getMem_pw().equals(rs.getString("Mem_pw"))) {
					
					sql = "delete from member where Mem_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getMem_id());
					result = pstmt.executeUpdate();
					System.out.println(" 회원 탈퇴 성공 ");
				} else {
					// 비밀번호 불일치
					result = 0;	
					System.out.println(" 탈퇴 실패 - 비밀번호 불일치 ");
					}
				} else{
					result = -1;
				}

		} catch (Exception e) {
			
		} finally {
			closeDB();
		}		
			
		return result;
		
	} // deleteMember
	
	// 회원 정보 조회 - memberInfo(String Mem_id)
	public MemberDTO memberInfo(String Mem_id){
			
		MemberDTO dto = null;
			
		try {
			con = getCon();
			
			sql = "select Mem_num,Mem_name,Mem_grade,rCount from member "
					+ " where Mem_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new MemberDTO();
				dto.setMem_num(rs.getInt("Mem_num"));
				dto.setMem_name(rs.getString("Mem_name")); ;
				dto.setMem_grade(rs.getString("Mem_grade"));
				dto.setRCount(rs.getInt("rCount"));;
				dto.setMem_id("Mem_id"); ;

			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
			
		return dto;
		
	} // getMemberInfo(String Mem_id)
	
	// 등급 조회 - memberInfo(String Mem_id)
	public MemberDTO getGradeInfo(String Mem_id){
			
		MemberDTO dto = null;
			
		try {
			con = getCon();
			
			sql = "select Mem_name,rCount from member "
					+ " where Mem_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new MemberDTO();
				dto.setMem_name(rs.getString("Mem_name")); ;
				dto.setRCount(rs.getInt("rCount"));;
				dto.setMem_id("Mem_id"); ;

			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
			
		return dto;
		
	} // memberInfo(String Mem_id)
	
	
	// 아이디 찾기 - findId(Mem_name, Mem_email)
	public MemberDTO findId(String Mem_name, String Mem_email) {
		
		MemberDTO dto = null;
		
		try {
			
			con = getCon();
			
			sql = "select * from member where Mem_name=? and Mem_email=?";
			
			pstmt = con.prepareStatement(sql);
			
	        pstmt.setString(1, Mem_name);
	        pstmt.setString(2, Mem_email);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	
	        	dto = new MemberDTO();
				dto.setMem_id(rs.getString("Mem_id"));
				
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		System.out.println(" M  : 아이디 찾기 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + dto);
		return dto;
	}
	// findId(Mem_name, Mem_email)
	
	
	// 비밀번호 찾기 - findPw(String Mem_id, String Mem_nam)
	public MemberDTO findPw(String Mem_name, String Mem_id) {
		
		MemberDTO dto = null;
		
		try {
			
			con = getCon();
			
			sql = "select * from member where Mem_name=? and Mem_id=?";
			
			pstmt = con.prepareStatement(sql);
			
	        pstmt.setString(1, Mem_name);
	        pstmt.setString(2, Mem_id);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	
	        	dto = new MemberDTO();
				dto.setMem_pw(rs.getString("Mem_pw"));
				
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		System.out.println(" M  : 비밀번호 찾기 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + dto);
		return dto;
	}
	// findPw(String Mem_id, String Mem_nam)

	
	// 카카오 회원가입 - kakaoJoin(MemberDTO dto)
	public void kakaoJoin(MemberDTO dto) {
		
		int Mem_num = 0;
		
		try {
			con = getCon();
			
			sql = "select max(Mem_num) from member";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Mem_num = rs.getInt(1)+1;
			}
			
			System.out.println(" DAO : Mem_num = " + Mem_num);
			
			sql = "insert into member(Mem_num,Mem_name,Mem_id,Mem_pw,Mem_phone,Mem_email) "
					+ "values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Mem_num);
			pstmt.setString(2, dto.getMem_name());
			pstmt.setString(3, dto.getMem_id());
			pstmt.setString(4, dto.getMem_pw());
			pstmt.setString(5, dto.getMem_phone());
			//pstmt.setDate(6, dto.getMem_birth());
			pstmt.setString(6, dto.getMem_email());
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 카카오 회원가입 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// kakaoJoin(DTO)
	
	
	// 카카오 로그인 - kakaoLogin(Mem_id, Mem_email)
	public int kakaoLogin(String Mem_id, String Mem_email) {
		
		int result = -1;
		
		try {

			con = getCon();

			sql = "select Mem_email from member where Mem_id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Mem_id);

			rs = pstmt.executeQuery();
			
			// 5. 
			if(rs.next()) {
				
				// 회원일 경우
				if(Mem_email.equals(rs.getString("Mem_email"))) {
					// 로그인 성공
					result = 1;
				}
			}else {
				// 비회원
				result = -1;
			}
			System.out.println(" DAO : 로그인 체크 = " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// kakaoLogin(Mem_id, Mem_email)
	
	

} // MemberDAO
