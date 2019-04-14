<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>

<%
	if (request.getParameter("menu_num") != null && request.getParameter("menu_num").equals("1")) {
%>
		<img alt="member1" src="/mvc/codingBooster/images/member_img.png" style="max-width:100%">
		<button onclick="getContent(2);" class="input_btn">다음</button>
	<%
	}else if(request.getParameter("menu_num") != null && request.getParameter("menu_num").equals("2")) {
%>
		<img alt="member2" src="/mvc/codingBooster/images/member_img2.png" style="max-width:100%">
		<button onclick="getContent(3);" class="input_btn">다음</button>
	<%
	}else {
%>
		<img alt="member3" src="/mvc/codingBooster/images/member_img3.png" style="max-width:100%">
		<button onclick="getContent(1);" class="input_btn">처음으로</button>
	<%
	}
%>

</body>

