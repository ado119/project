<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvc.R.RSurveyRe, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/mvc/codingBooster/css/bootstrap.css">
<link rel="stylesheet" href="/mvc/codingBooster/css/codingBooster.css">
<link rel="shortcut icon" href="/mvc/codingBooster/images/favicon.ico">
<link rel="icon" href="/mvc/codingBooster/images/favicon.ico">


<meta charset="UTF-8">
<title>서울왕오빠픽</title>
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
					<li class="active"><a href="/mvc/codingBooster/jsp/survey.jsp">창업 추천</a></li>
					<li><a href="/mvc/agora">게시판</a></li>
					<!-- 누르면 게시판 이동 -->
					<li><a href="/mvc/codingBooster/jsp/Faq.jsp">문의</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
						String loginUser = (String)session.getAttribute("loginUser");
						if(loginUser == null){
							loginUser = "비회원";
							
					%>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/mvc/codingBooster/jsp/pro_login.jsp" class="member_class">로그인</a></li>
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
	<div >
		<br><br><br><br><br><br><br>
		<div style="text-align:center; font-family:'배달의민족 한나는 열한살';">
		<img src="/mvc/codingBooster/images/background_img5.png" style="width: 60%;">
		<br><br><br>
		<%
			ArrayList<String> survey_list = (ArrayList<String>) request.getAttribute("survey_list");
			if (survey_list != null) {		
		%>	
				<h2><span style="color:#dc9013;font-family:'배달의민족 한나는 열한살';"><%=loginUser%></span> 님의 입력 값</h2>
				<h2>업종: <%=request.getAttribute("kind")%></h2>
				<h2>임대료: <%=request.getAttribute("budgets")%>만원</h2>
				<h2>원하는 평수: <%=request.getAttribute("spaces")%>평</h2>
				<hr>
				<div style="border:5px solid #dc9013; width:800px; margin-left:auto; margin-right:auto; font-family:'배달의민족 한나는 열한살';">
				<h2><span style="color:#dc9013; font-family:'배달의민족 한나는 열한살';"><%=loginUser%></span> 님의 추천 결과</h2>
				<h1>TOP1 <%=survey_list.get(0) %>&nbsp;&nbsp;&nbsp;TOP2 <%=survey_list.get(1) %>&nbsp;&nbsp;&nbsp; TOP3 <%=survey_list.get(2) %></h1>		
				</div>
				<br><br>
		<%
			}else{
		%>
				<p>list가 null이에요!</p>
		<%
			}
		%>
		</div>
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
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/mvc/codingBooster/js/bootstrap.js"></script>

</body>
</html>