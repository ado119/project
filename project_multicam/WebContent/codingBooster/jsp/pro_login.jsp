<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%String msg = (String)request.getAttribute("msg");%> --%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/mvc/codingBooster/css/bootstrap.css">
<link rel="stylesheet" href="/mvc/codingBooster/css/codingBooster.css">
<link rel="shortcut icon" href="/mvc/codingBooster/images/favicon.ico">
<link rel="icon" href="/mvc/codingBooster/images/favicon.ico">
<style>
.btn_col {
	background-color: #dc9013;
	color : white;
	border-radius: 5px;
	border: opx;
	width : 100%;
}

#login_id,
#login_pass {
	border : 1px solid #dc9013;
	border-left : 5px solid #dc9013;
}

</style>
<meta charset="UTF-8">
<title>서울왕오빠픽</title>
</head>
<body>
	<nav class="navbar navbar-default"
		style="width: 100%; position: fixed;" id="nav_test">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-oniy"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/mvc/codingBooster/index.jsp"><img
					src="/mvc/codingBooster/images/logo.png" id="logo"></a>
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
					<li><a href="/mvc/codingBooster/jsp/Faq.jsp">문의</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/mvc/codingBooster/jsp/pro_login.jsp" class="member_class">로그인</a></li>
							<li><a href="/mvc/codingBooster/pro_join.html"
								class="member_class">회원가입</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="nine_form">	
	<br><br><br><br><br><br><br><br><br><br>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
					<div class="login-box well">
						<form method="post" action="/mvc/pro_join">
							<h1>로그인</h1>
							<br><br>
							<label>ID : </label><input type="text" class="form-control" id="login_id"
								name="join_id"><br> <label>Password : </label>
								<input type="password" id="login_pass" name="join_pass" class="form-control">
							<input type="hidden" name="member" value="login"> <br>
							<input type="submit" value="Login" class="btn btn-default btn-block m-t-md btn_col">
							<button class="btn btn-default btn-block m-t-md btn_col" onclick="go_join(); return false;" >Join</button>
						</form>
						<!-- dc0913 -->
					</div>
				</div>
			</div>
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
	
	
	<script>
		
		
		function go_join() {
			location.href = "/mvc/codingBooster/pro_join.html";
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/mvc/codingBooster/js/bootstrap.js"></script>
</body>
</html>