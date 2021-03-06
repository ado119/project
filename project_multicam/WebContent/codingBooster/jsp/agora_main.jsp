<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.vo.AgoraVO, java.util.ArrayList"%>
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
a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: underline;
}

.show_form {
	width: 90%;
	margin-left: auto;
	margin-right: auto;
}

.show_form td, .show_form th {
	border: 1px solid #ddd;
	padding: 8px;
}

.show_form th {
	background-color: #dc9013;
	color: white;
}

.input_btn {
	background-color: #dc9013;
	border-color: #dc9013;
	color: white;
	border: opx;
	width: 150px;
	height: 30px;
	float: right;
}

#agora_num {
	width: 10%;
}

#agora_title {
	width: 30%;
}

#agora_id {
	width: 10%;
}

#agora_writedate {
	width: 20%;
}

#agora_cnt {
	width: 10%;
}
</style>

</head>
<body>
	<nav class="navbar navbar-default"
		style="width: 100%; position: fixed;">
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
							<li><a href="/mvc/codingBooster/jsp/map_type_rent.jsp"
								class="member_class">임대료<b>·</b>시세
							</a></li>
							<li><a href="/mvc/codingBooster/jsp/map_type_b.jsp"
								class="member_class">지역별 점포수</a></li>
							<li><a href="/mvc/codingBooster/jsp/crwal_hot.jsp"
								class="member_class">먹거리 트렌드</a></li>
						</ul></li>
					<li><a href="/mvc/codingBooster/jsp/survey.jsp">창업 추천</a></li>
					<li class="active"><a href="/mvc/agora">게시판</a></li>
					<!-- 누르면 게시판 이동 -->
					<li><a href="/mvc/codingBooster/jsp/Faq.jsp">문의</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
						String loginUser = (String) session.getAttribute("loginUser");
						if (loginUser == null) {
					%>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/mvc/codingBooster/jsp/pro_login.jsp"
								class="member_class">로그인</a></li>
							<li><a href="/mvc/codingBooster/pro_join.html"
								class="member_class">회원가입</a></li>
						</ul></li>
					<%
						} else {
					%>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" rolo="button" aria-hashpopup="true"
						aria-expanded="false">회원<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/mvc/pro_join?member=update&id=<%=loginUser %>" class="member_class">회원정보 수정</a></li>
							<li><a href="/mvc/pro_join?member=logout"
								class="member_class">로그아웃</a></li>
						</ul></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>
	<div class="nine_form">
		<br>
		<br>
		<br> <br>
		<h1 class="agora_content">
			<b>공유게시판</b>
		</h1>

		<%
			ArrayList<AgoraVO> list = (ArrayList<AgoraVO>) request.getAttribute("list");
			int page_ccc=1; 
			if (list != null) {
		%>
		<form method="get" , action="/mvc/agora" class="table">
			<table class="show_form">
				<!-- 테이블 작성  for문은 에러 방지용 테스트 코드-->
				<tr>
					<th id="agora_num">번호</th>
					<th id="agora_title">제목</th>
					<th id="agora_id">작성자</th>
					<th id="agora_writedate">작성일</th>
					<th id="agora_cnt">조회</th>
				</tr>
				<%
					for (AgoraVO vo : list) {
				%>
				<tr>
					<td><%=vo.getAgora_num()%></td>
					<td><a
						href="/mvc/agora?action=showcontent&agora_num=<%=vo.getAgora_num()%>"><%=vo.getTitle()%></td>
					<td><%=vo.getId()%></td>
					<td><%=vo.getWritedate()%></td>
					<td><%=vo.getCnt()%></td>
				</tr>
				<%
					}
				%>
			</table>
			
			<%
				String loginUser2 = (String) session.getAttribute("loginUser");
			
					if (loginUser2 == null) {

					} else {
			%>
			<br>
			<div class="agora_content2">
				<a href="/mvc/codingBooster/jsp/agora_write.jsp"><input
					type="button" value="의견 작성하기" class="input_btn"></a>
			</div>
			<br>
			
			<%
				}
			%>
		</form>
		<%
			} else {
		%>
		<p>list가 null이에요!</p>
		<%
			}
		%>
		<div style="text-align:center; font-size: 17px;">
		<%
			int paging_count = (int)request.getAttribute("paging");
			
			for(int j=1; j<=paging_count; j++){
		%>
			<a href="/mvc/agora?page_n=<%=j%>" style="color:black"><%=j%></a>&nbsp;&nbsp;&nbsp;
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
					<p>
						서울왕오빠픽은 빅데이터 기술을 활용해 사용자에게 <br>창업 장소를 추천해주는 서비스입니다.
					</p>
				</div>
				<div class="col-sm-2">
					<h4 style="text-align: center;">내비게이션</h4>
					<div class="list-group">
						<a href="/mvc/codingBooster/jsp/member.jsp"
							class="list-group-item">소개</a> <a
							href="/mvc/codingBooster/jsp/survey.jsp" class="list-group-item">창업
							추천</a> <a href="/mvc/codingBooster/jsp/Faq.jsp"
							class="list-group-item">문의</a>
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