<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서울왕오빠픽</title>
<link rel="stylesheet" href="/mvc/codingBooster/css/bootstrap.css">
<link rel="stylesheet" href="/mvc/codingBooster/css/codingBooster.css">
<link rel="shortcut icon" href="/mvc/codingBooster/images/favicon.ico">
<link rel="icon" href="/mvc/codingBooster/images/favicon.ico">
<style>
th {
	text-align: left;
	border-bottom: 1px solid black;
}

td {
	
}

.faq_table {
	border-collapse: collapse !important;
	width: 70%;
	margin-right: auto;
	margin-left: auto;
}

#pre {
	width: 100%;
}
</style>

</head>
<body>
	<nav class="navbar navbar-default" style="width:100%;position:fixed;">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-oniy"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/mvc/codingBooster/index.jsp"><img src="/mvc/codingBooster/images/logo.png" id="logo"></a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/mvc/codingBooster/index.jsp">HOME<span
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
					<li class="active"><a href="/mvc/codingBooster/jsp/Faq.jsp">문의</a></li>
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
							<li><a href="pro_login.jsp" class="member_class">로그인</a></li>
							<li><a href="/mvc/codingBooster/pro_join.html" class="member_class">회원가입</a></li>
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
	<div class="nine_form">
	
		<br><br><br><br>
		<h1 class="faq_header"><b>자주 나오는 질문</b></h1>
		<table class="faq_table">	
			<tr id="button1" onclick="faq_b1()">
				<th class="faq_tr">Q. 웹페이지 오류 문의는 어디로 하나요?</th>
			</tr>
			<tr style="display: none;" id="table1">
				<td class="faq_pre"><span>A: 서울왕오빠픽 이용중에 문제점이 발생되어 죄송합니다.
	  			<br>해당 문의는 ado119@naver.com으로 문의 주시면 해결하겠습니다. 
	   			<br>감사합니다.</span></td>
			</tr>
			<tr id="button2" onclick="faq_b2()">
				<th class="faq_tr">Q. 아이디를 까먹었어요.</th>
			</tr>
			<tr style="display: none;" id="table2">
				<td class="faq_pre">A: 인증 문제는 사업 신청이 이루어져야 인증 관련 API를 가져올 수 있습니다.
				<br>아직 사업신청이 되지 않아 해당 서비스는 지원이 불가합니다.
				<br>해당 문의는 dbdud617@naver.com으로 문의 주시면 해결하겠습니다.</td>
			</tr>
			<tr id="button3" onclick="faq_b3()">
				<th class="faq_tr">Q. 추천 알고리즘에 사용한 데이터의 출처가 어디인가요?</th>
			</tr>
			<tr style="display: none;" id="table3">
				<td class="faq_pre">A: 공공 데이터 포털(https://www.data.go.kr/), 국가통계포탈(http://kosis.kr/index/index.do)의 <br>
				데이터를 사용하였습니다.</td>
			</tr>
			<tr id="button4" onclick="faq_b4()">
				<th class="faq_tr">Q. 몇 개년의 데이터를 사용하시나요?</th>
			</tr>
			<tr style="display: none;" id="table4">
				<td class="faq_pre">A: 저희 서울왕오빠픽은 총 4개년의 데이터를 사용하여 추천해드리고 있습니다.</td>
			</tr>
		</table>
	</div>
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
	
	<script>
	function faq_b1(){
		var con = document.getElementById("table1");
		if(con.style.display == "none"){
			con.style.display = "block";
			//
			
		}else{
			con.style.display = "none";
		}
	}
	function faq_b2(){
		var con = document.getElementById("table2");
		if(con.style.display == "none"){
			con.style.display = "block";
			//
		}else{
			con.style.display = "none";
		}
	}
	function faq_b3(){
		var con = document.getElementById("table3");
		if(con.style.display == "none"){
			con.style.display = "block";
			//
		}else{
			con.style.display = "none";
		}
	}

	function faq_b4(){
		var con = document.getElementById("table4");
		if(con.style.display == "none"){
			con.style.display = "block";
			//
		}else{
			con.style.display = "none";
		}
	}
</script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/mvc/codingBooster/js/bootstrap.js"></script>
</body>
</html>