
<%@page import="org.sp.board.domain.Dog"%>
<%@page import="org.sp.board.domain.Admin"%>

<%@page import="java.util.List"%>
<%@page import="org.sp.board.util.Pager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Pager pager = (Pager) request.getAttribute("pager");
	List<Dog> dogList = (List) request.getAttribute("dogList");
	Admin admin = (Admin) session.getAttribute("admin");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #333;
}

.topnav a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #04AA6D;
	color: white;
}

.topnav .icon {
	display: none;
}

@media screen and (max-width: 600px) {
	.topnav a:not(:first-child) {
		display: none;
	}
	.topnav a.icon {
		float: right;
		display: block;
	}
}

@media screen and (max-width: 600px) {
	.topnav.responsive {
		position: relative;
	}
	.topnav.responsive .icon {
		position: absolute;
		right: 0;
		top: 0;
	}
	.topnav.responsive a {
		float: none;
		display: block;
		text-align: left;
	}
}
</style>
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 80%;
	border: 1px solid #ddd;
	margin: auto;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	float: right;
}

a {
	text-decoration: none;
}

input[type=button]:hover {
	background-color: #45a049;
}

h1 {
	margin-left: 150px;
}
</style>


<%@ include file="../inc/head_link.jsp"%>
<script type="text/javascript">
	$(function() {
		
	});
</script>

</head>
<body>
	<div class="topnav">
		<a class="active" href="#home">Home</a> 
		<a href="/admin/book/list">예약 관리</a> 
		<a href="#about">About</a>
		<h3><%=admin.getName()%>님 로그인 중
		</h3>
	</div>
	
	<h1>예약 현황 목록</h1>
	<br>

	<table>

		<tr>
			<th>NO</th>
			<th>예약자</th>
			<th>예약 날짜</th>
			<th>예약 시간</th>
			<th>예약 여부</th>
		</tr>
		<%
			int num = pager.getNum();
		%>
		<%
			int curPos = pager.getCurPos();//페이지당 List의 시작 index
		%>
		<%
			for (int i = 1; i <= pager.getPageSize(); i++) {
		%>
		<%
			if (num < 1)
			break;
		%>
		<%
			Dog dog = dogList.get(curPos++);
		%>
		<tr>
			<td><%=num--%></td>
			<td><a href="/admin/book/content?dog_idx=<%=dog.getDog_idx()%>"><%=dog.getName()%></td>
			<td><%=dog.getBookdate()%></td>
			<td><%=dog.getBooktime()%></td>
			<td><%=dog.getComfirmed()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="5">
				<%
					if (pager.getFirstPage() - 1 < 1) {
				%> <a
				href="javascript:alert('이전 페이지가 없습니다.');">◀</a> <%
 	} else {
 %> <a
				href="/admin/book/list?currentPage=<%=pager.getFirstPage() - 1%>">◀</a> <%
 	}
 %>
				<%
					for (int i = pager.getFirstPage(); i <= pager.getLastPage(); i++) {
				%> <%
 	if (i > pager.getTotalPage())
 	break;
 %>
				<a href="/admin/book/list?currentPage=<%=i%>">[<%=i%>]
			</a> <%
 	}
 %> <%
 	if (pager.getLastPage() + 1 > pager.getTotalPage()) {
 %> <a
				href="javascript:alert('마지막 페이지 입니다.')">▶</a> <%
 	} else {
 %> <a
				href="/admin/book/list?currentPage=<%=pager.getLastPage() + 1%>">▶</a> <%
 	}
 %>
			</td>

		</tr>

	</table>

</body>
</html>
