<%@page import="org.sp.board.domain.Board"%>
<%@page import="java.util.List"%>
<%@page import="org.sp.board.util.Pager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Pager pager=(Pager)request.getAttribute("pager");
	List<Board> boardList=(List)request.getAttribute("boardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 80%;
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
h1{
	margin-left:150px; 
}
</style>
<%@ include file="../inc/head_link.jsp" %>
<script type="text/javascript">
$(function(){
	$("#bt_regist").click(function(){
		location.href="/board/registform";
	});
});


</script>
</head>
<body>
	<h2>
		<a href="/admin/loginform">관리자 모드</a>
	</h2>
	<h1>자유게시판</h1>
	<br>
	<table style="border:none">
		<tr>
			<td colspan="5" align="right"><input type="button" value="글 등록" id=bt_regist></td>
		</tr>
	</table>

	<table>

		<tr>
			<th>NO</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<%int num=pager.getNum(); %>
		<%int curPos=pager.getCurPos();//페이지당 List의 시작 index %>
		<%for(int i=1; i<=pager.getPageSize();i++){ %>
		<%if(num<1)break; %>
		<%Board board=boardList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><%=board.getWriter() %></td>
			<td><a href="/board/content?board_idx=<%=board.getBoard_idx()%>"><%=board.getTitle() %></a></td>
			<td><%=board.getHit() %></th>
			<td><%=board.getRegdate().substring(0, 10) %></th>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
				<%if(pager.getFirstPage()-1<1){ %>
					<a href="javascript:alert('이전 페이지가 없습니다.');">◀</a>
				<%}else{ %>
					<a href="/board/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				<%} %>
				<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){ %>
				<%if(i>pager.getTotalPage())break; %>
					<a href="/board/list?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				<%if(pager.getLastPage()+1>pager.getTotalPage()){ %>
					<a href="javascript:alert('마지막 페이지 입니다.')">▶</a>
				<%}else{ %>
					<a href="/board/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
				<%} %>
			</td>
			
		</tr>

	</table>

</body>
</html>
