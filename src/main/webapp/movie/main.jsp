<%@page import="java.util.HashMap"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Nectaria - Free HTML Template by WowThemes.net</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">


		


</script>
<%
	 	LocalDate nowDate = LocalDate.now();
		LocalDate beforOnDate = LocalDate.now().minusDays(1);
		String yesterday = beforOnDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.print(yesterday);
	    // 파라메터 설정
		String targetDt = request.getParameter("targetDt")==null?yesterday:request.getParameter("targetDt");			//조회일자
		String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");		//결과row수
		String multiMovieYn = request.getParameter("multiMovieYn")==null?"":request.getParameter("multiMovieYn");		//“Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
		String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");			//“K: : 한국영화 “F” : 외국영화 (default : 전체)
		String wideAreaCd = request.getParameter("wideAreaCd")==null?"":request.getParameter("wideAreaCd");				//“0105000000” 로서 조회된 지역코드
	
		// 발급키
		String key = "09a2696ef753c4b8196ac759ba9b0007";
		// KOBIS 오픈 API Rest Client를 통해 호출
	    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
	
		// 일일 박스오피스 서비스 호출 (boolean isJson, String targetDt, String itemPerPage,String multiMovieYn, String repNationCd, String wideAreaCd)
	    String dailyResponse = service.getDailyBoxOffice(true,targetDt,itemPerPage,multiMovieYn,repNationCd,wideAreaCd);
	
		// Json 라이브러리를 통해 Handling
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String,Object> dailyResult = mapper.readValue(dailyResponse, HashMap.class);
	
		request.setAttribute("dailyResult",dailyResult);
	
		// KOBIS 오픈 API Rest Client를 통해 코드 서비스 호출 (boolean isJson, String comCode )
		String codeResponse = service.getComCodeList(true,"0105000000");
		HashMap<String,Object> codeResult = mapper.readValue(codeResponse, HashMap.class);
		request.setAttribute("codeResult",codeResult);
		
		
		
		
		
    %>


</head>
<body class="post-template page-template page">
<div class="site-wrapper">
	<nav class="main-nav overlay clearfix">
	<a class="blog-logo" href="index.html"><img src="assets/img/logo.png" alt="Fashion Critiques"/></a>
	<ul id="menu">
		<li class="nav-home nav-current" role="presentation"><a href="index.html">Home</a></li>
		<li class="nav-article-example" role="presentation"><a href="article.html">Post Example</a></li>
		<li class="nav-about-us" role="presentation"><a href="about.html">Page Example</a></li>
		<li class="nav-author-page" role="presentation"><a href="author.html">Author Page</a></li>
		<span class="socialheader">
		<a href="#"><span class='symbol'>circletwitterbird</span></a>
		<a href="#"><span class='symbol'>circlefacebook</span></a>
		<a href="#"><span class='symbol'>circlegoogleplus</span></a>
		<a href="mailto:wowthemesnet@gmail.com"><span class='symbol'>circleemail</span></a>
		</span>
	</ul>
	</nav>
	<header class="main-header post-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/10/10174958/fas-compressor.jpg)">
	<div class="vertical">
		<div class="main-header-content inner">
		
		<c:if test="${not empty dailyResult.boxOfficeResult.dailyBoxOfficeList}">
		
		<c:forEach items="${dailyResult.boxOfficeResult.dailyBoxOfficeList}" var="boxoffice">
			<h1 class="post-title"><a href="movieList.mv">영화목록</a></h1>
		</c:forEach>	
			
		</c:if>	
			
			
			
			
			
		</div>
	</div>
	</header>
	<main class="content" role="main">
	<article class="post tag-fashion tag-art page">
	<section class="post-content">
	<p>
		<strong>The blog combining journalist David's years of experience covering fashion and culture for among others. Read my blog and you will learn how to become a fashion editor.</strong>
	</p>
	<p>
		A decanter of aguardiente, a bowl of loaf sugar, and a pitcher of cold water from the spring, were set before us, and, being duly honoured, had a most reviving influence upon our spirits as well as our corporeal energies.
	</p>
	<p>
		Suspended from the walls of the room were numerous coarse engravings, highly coloured with green, blue, and crimson paints, representing the Virgin Mary, and many of the saints. These engravings are held in great veneration by the devout Catholics of this country. In the corners of the room were two comfortable-looking beds, with clean white sheets and pillow-cases, a sight with which my eyes have not been greeted for many months.
	</p>
	<p>
		Mr. Livermore has been a resident of California nearly thirty years, and, having married into one of the wealthy families of the country, is the proprietor of some of the best lands for tillage and grazing. An arroyo, or small rivulet fed by springs, runs through his rancho, in such a course that, if expedient, he could, without much expense, irrigate one or two thousand acres. Irrigation in this part of California, however, seems to be entirely unnecessary for the production of wheat or any of the small grains. To produce maize, potatoes, and garden vegetables, irrigation is indispensable. Mr. Livermore has on his rancho about 3500 head of cattle. His horses, during the late disturbances, have nearly all been driven off or stolen by the Indians.
	</p>
	<p>
		I saw in his corral a flock of sheep numbering several hundred. They are of good size, and the mutton is said to be of an excellent quality, but the wool is coarse. It is, however, well adapted to the only manufacture of wool that is carried on in the country, coarse blankets and serapes. But little attention is paid to hogs here, although the breeds are as fine as I have ever seen elsewhere. Beef being so abundant, and of a quality so superior, pork is not prized by the native Californians.
	</p>
	</section>
	</article>
	</main>
	<footer class="site-footer clearfix">
	<a href="#top" id="back-to-top" class="back-top"></a>
	<div class="text-center">
		Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a>

	</div>
	</footer>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>