<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.vo.AgoraVO, model.vo.CommVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/mvc/codingBooster/css/bootstrap.css">
<link rel="stylesheet" href="/mvc/codingBooster/css/codingBooster.css">
<link rel="shortcut icon" href="/mvc/codingBooster/images/favicon.ico">
<link rel="icon" href="/mvc/codingBooster/images/favicon.ico">
<meta charset="UTF-8">

<style>
.show_form{
	width:80%;
	margin-left: auto;
	margin-right:auto;
}
.show_form td, .show_form th{
	border: 1px solid #ddd;
  	padding: 8px;
}
.show_form th{
	background-color: #dc9013;
  	color: white;
}
.comment_form{
	width: 80%;
	height: 90px;
	margin-left: auto;
	margin-right:auto;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}
.input_form{
	width:80%;
	margin-left: auto;
	margin-right:auto;
}
.input_btn{
	background-color:#dc9013; 
	color:white;
	border: opx;
	border-color: #dc9013;
	float: right;
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
#agora_content {
	height: 250px;
}

</style>

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
					<li><a href="/mvc/codingBooster/jsp/survey.jsp">창업 추천</a></li>
					<li class="active"><a href="/mvc/agora">게시판</a></li>
					<!-- 누르면 게시판 이동 -->
					<li><a href="/mvc/codingBooster/jsp/Faq.jsp">문의</a></li>
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
	<div class="nine_form">
		<br><br><br><br>
		<%
			ArrayList<AgoraVO> list = (ArrayList<AgoraVO>) request.getAttribute("list");
			AgoraVO vo = (AgoraVO) request.getAttribute("vo");
			
			if (vo != null) {
		%>
		<h1 class="agora_content"><b>공유게시판</b></h1>
		<table class="show_form">
			<tr>
				<th id="agora_title"><%=vo.getTitle()%></th>
				<th id="agora_id"><%=vo.getId()%></th>
				<th id="agora_writedate"><%=vo.getWritedate()%></th>
				<th id="agora_cnt"><%=vo.getCnt()%></th>
			</tr>
			<tr>
				<td id="agora_content" colspan="4"><%=vo.getContent()%></td>
			</tr>
		</table>
		<div class="agora_content3">
			<% 
			if(loginUser.equals(vo.getId())){
			%>
			<a href="/mvc/agora?action=delete&agora_num=<%=vo.getAgora_num()%>"><input type="button" value="삭제" style="width:100px; border: opx; border-color: #dc9013; margin-top:5px; height:30px; background-color:#dc9013;"></a>
			<%
			}
			%>
			<a href="/mvc/agora" style="color:black"><input type="button" value="목록으로" class="input_btn" style="width:150px; margin-top:5px; height:30px;"></a>
		</div>
		<br>
		<h4 class="agora_content3"><b>댓글</b></h4>
		<div>
			<form class="input_form" method="post" action="/mvc/agora">
			<%-- <a href="/project/agora_update.jsp"><input type="button" value="수정"></a>--%> 
				<input type="text" name="comment_sc" placeholder="댓글을 달아주세요." style="width:85% ;height:70px;">
				<input type="hidden" value="comment" name="comm"> 
				<input type="hidden" value=<%=vo.getAgora_num() %> name="agora_select">
				<input type="submit" value="댓글 달기" class="input_btn" style="width:14%; height:70px;">
			</form>
		</div>
		<br>
			<%
				ArrayList<CommVO> comm_list = (ArrayList<CommVO>) request.getAttribute("comm_vo");
				/* CommVO vo2 = (CommVO) request.getAttribute("comm_vo"); */
				if(comm_list != null){
					for(CommVO vo2 : comm_list){			
			%>
				<table class="comment_form">
					<tr>
						<td><img src = "/mvc/codingBooster/images/id.png" width=31px>  <b><%=vo2.getId() %></b></td>
					</tr>
					<tr>
						<td><%=vo2.getComm() %></td>
					</tr>
					<tr>
						<td><%=vo2.getWritedate() %></td>
					</tr>
				</table>
		<%
					}
				}
			}
		%>
		
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