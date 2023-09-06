<%@page import="org.sp.board.util.Pager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Pager pager=(Pager)request.getAttribute("pager");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 98%;
	border: 1px solid #ddd;
	margin:auto;

	
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
a{
	text-decoration:none;
}


input[type=button]:hover {
  background-color: #45a049;
}
</style>
<%@ include file="./inc/head_link.jsp" %>
<script type="text/javascript">
$(function(){
	$("#bt_regist").click(function(){
		location.href="/board/registform";
	});
});


</script>
</head>
<body>

	<h2>자유게시판</h2>
	<br>

	<table>

		<tr>
			<td colspan="5" align="right"><input type="button" value="글 등록" id=bt_regist></td>
			
		</tr>
		<tr>
			<th>NO</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<%for(int i=1; i<=pager.getPageSize();i++){ %>
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
			<td>조회수</th>
			<td>등록일</th>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
				<a href="/board/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){ %>
				<%if(i>pager.getTotalPage())break; %>
					<a href="/board/list?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				<a href="/board/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			</td>
			
		</tr>

	</table>

</body>
</html>
