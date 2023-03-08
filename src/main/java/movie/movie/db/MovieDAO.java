package movie.movie.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

public class MovieDAO {
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
	
	//saveDB(MovieDTO dto)
	public void saveDB(MovieDTO dto) {
		
		System.out.println(dto.getM_playDate());
		
		try {
			con = getCon();
			sql = "insert into movie (M_num, M_name, M_playDate) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getM_num());
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_playDate());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
	}	//saveDB(MovieDTO dto)
	
	

	
	
	// getMovieAPI()
	public List getmovieAPI() {
		List dailyList = new ArrayList<>();
		
		MovieDTO dto = null;
		String dailyResponse = "";
		LocalDate beforOnDate = LocalDate.now().minusDays(1);
		String yesterday = beforOnDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println("어제 날짜 : " + yesterday);
		
	    // 파라메터 설정
		String targetDt = yesterday;			//조회일자
		String itemPerPage = "10";		//결과row수
		String multiMovieYn = "";		//“Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
		String repNationCd = "";	//“K: : 한국영화 “F” : 외국영화 (default : 전체)
		String wideAreaCd = "";			//“0105000000” 로서 조회된 지역코드
	
		// 발급키
		String key = "09a2696ef753c4b8196ac759ba9b0007";
		// KOBIS 오픈 API Rest Client를 통해 호출
	    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
	
		// 일일 박스오피스 서비스 호출 (boolean isJson, String targetDt, String itemPerPage,String multiMovieYn, String repNationCd, String wideAreaCd)
	    try {
			dailyResponse = service.getDailyBoxOffice(true,targetDt,itemPerPage,multiMovieYn,repNationCd,wideAreaCd);
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(dailyResponse);
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject boxOfficeResult = (JSONObject)jsonObject.get("boxOfficeResult");
			
			String boxofficeType = (String)boxOfficeResult.get("boxofficeType");
			String showRange = (String)boxOfficeResult.get("showRange");
			
			System.out.println(boxofficeType);
			System.out.println(showRange);
			
			JSONArray dailyBoxOfficeList = (JSONArray)boxOfficeResult.get("dailyBoxOfficeList");
			
			
			for(int i=0; i<dailyBoxOfficeList.size(); i++) {
				dto = new MovieDTO();
				JSONObject dailyBoxOffice = (JSONObject)dailyBoxOfficeList.get(i);
				
				dto.setM_num(dailyBoxOffice.get("movieCd").toString());
				dto.setM_name(dailyBoxOffice.get("movieNm").toString());
				dto.setM_rank(dailyBoxOffice.get("rnum").toString());
				dto.setM_reservationRate(dailyBoxOffice.get("salesShare").toString());
				dto.setM_playDate(dailyBoxOffice.get("openDt").toString());

				dailyList.add(dto);	
			}		
		} catch (OpenAPIFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dailyList;
	} // getMovieAPI()
	
	
	
	
	
	
	
}




































