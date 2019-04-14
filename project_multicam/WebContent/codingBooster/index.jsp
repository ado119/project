<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width" , initial-scale="1">
<title>서울왕오빠픽</title>
<link rel=" shortcut icon" href="/mvc/codingBooster/images/favicon.ico">
<link rel="icon" href="/mvc/codingBooster/images/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/codingBooster.css">
</head>
<body>
	<style>
#slidebox {
	position: relative;
	width: 100%;
	height: 400px;
	overflow: hidden;
	white-space: nowrap;
	/* border:1px solid #000; */
	
}
#slidebox ul#slider {
	list-style: none;
	margin: 0;
	padding: 0;
}
#slidebox ul li {
	position: absolute;
	width: 100%;
	height: 400px;
}
#slidebox ul li img {
	width: 100%;
	height: 400px;
}
a:hover {
	color: #ffffff;
	background-color: #000000;
}
a {
	background-color:#292823; 
}
</style>
	<nav class="navbar navbar-default"
		style="width: 100%; position: fixed;">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-oniy"></span> <span class="icon-bar"></span> 
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/mvc/codingBooster/index.jsp"><img src="/mvc/codingBooster/images/logo.png" id="logo"></a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/mvc/codingBooster/index.jsp">HOME<span
							class="sr-oniy"></span></a></li>
					<li><a href="/mvc/codingBooster/jsp/member.jsp">소개</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">창업 현황<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/mvc/codingBooster/jsp/map_type_rent.jsp" class="member_class">임대료<b>·</b>시세</a></li>
							<li><a href="/mvc/codingBooster/jsp/map_type_b.jsp" class="member_class">지역별 점포수</a></li>
							<li><a href="/mvc/codingBooster/jsp/crwal_hot.jsp" class="member_class">먹거리 트렌드</a></li>
						</ul>
					</li>
					<li><a href="/mvc/codingBooster/jsp/survey.jsp">창업 추천</a></li>
					<li><a href="/mvc/agora">게시판</a></li>
					<!-- 누르면 게시판 이동 -->
					<li><a href="jsp/Faq.jsp">문의</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
						String loginUser = (String)session.getAttribute("loginUser");
						if(loginUser == null){
							
					%>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="jsp/pro_login.jsp" class="member_class">로그인</a></li>
							<li><a href="pro_join.html" class="member_class">회원가입</a></li>
						</ul></li>
					<%
						}else{
					%>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">회원<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/mvc/pro_join?member=update&id=<%=loginUser %>" class="member_class">회원정보 수정</a></li>
							<li><a href="/mvc/pro_join?member=logout" class="member_class">로그아웃</a></li>
						</ul></li>
					<%} %>
				</ul>
			</div>
		</div>
	</nav>
	<br><br><br><br>
	<div class="container" id="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">

				<div class="item active">
					<img src="images/background_img1.png" alt="test1" style="width: 100%;">
					<div class="carousel-caption"></div>
				</div>
				<div class="item">
					<img src="images/background_img2.png" alt="test2" style="width: 100%;">
					<div class="carousel-caption"></div>
				</div>
				<div class="item">
					<img src="images/background_img3.png" alt="test2" style="width: 100%;">
					<div class="carousel-caption"></div>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<br><br>
		<div class="row" id="row_center">
			<div class="col-md-4" onclick="go_member()">
				<img alt="img1" src="images/navi1.png"
					style="max-width: 100%; ">
			</div>
			<div class="col-md-4" onclick="go_servey()">
				<img alt="img2" src="images/navi2.png"
					style="max-width: 100%;">
			</div>
			<div class="col-md-4" onclick="go_sns()">
				<img alt="img3" src="images/navi3.png"
					style="max-width: 100%;">
			</div>
		</div>
	</div>
	<br><br>
	<footer style="background-color: #292823; color: #ffffff">
		<div class="container" id="container">
			<br>
			<div class="row">
				<div class="col-sm-2" style="text-align: left;">
					<h5>Copyright &copy; 2019.</h5>
					<h5>서울왕오빠들.</h5>
					<h5>All rights reserved.</h5>
				</div>
				<div class="col-sm-5">
					<h4>서울왕오빠픽이란?</h4>
					<p>서울왕오빠픽은 빅데이터 기술을 활용해 사용자에게
					<br>창업 장소를 추천해주는 서비스입니다.</p>
				</div>
				<div class="col-sm-2">
					<h4 style="text-align: center;">내비게이션</h4>
					<div class="list-group">
						<a href="/mvc/codingBooster/jsp/member.jsp" class="list-group-item">소개</a>
						<a href="/mvc/codingBooster/jsp/survey.jsp" class="list-group-item">창업 추천</a>
						<a href="/mvc/codingBooster/jsp/Faq.jsp" class="list-group-item">문의</a>
					</div>
				</div>
				<div class="col-sm-3">
					<h4 style="text-align: center;">by 멀티캠퍼스</h4>
				</div>
			</div>
		</div>
	</footer>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript">
		function go_member(){
			location.href="/mvc/codingBooster/jsp/member.jsp";
		}
		function go_servey() {
			location.href="/mvc/codingBooster/jsp/survey.jsp";
		}
		function go_sns() {
			location.href="/mvc/codingBooster/jsp/crwal_hot.jsp";			
		}
	</script>
</body>
</html>