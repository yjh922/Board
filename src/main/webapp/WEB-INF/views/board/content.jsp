<%@page import="org.sp.board.domain.BoardFile"%>
<%@page import="org.sp.board.domain.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<%@ include file="./inc/head_link.jsp" %>
<script type="text/javascript">

function regist(){
	$("form").attr({
		action:"/board/regist",
		method:"post",
		enctype:"multipart/form-data"
	});
	$("form").submit();
}


$(function(){
	$("#bt_edit").click(function(){
		
	});
	$("#bt_del").click(function(){
		if(confirm("삭제하시겠어요?")){
			//삭제 요청시 form 태그 안에 작성된 파라미터들을 한꺼번에 전송하자
			$("form").attr({
				action:"/board/delete",
				method:"post"
			});
			$("form").submit();
			
		}
	});
	$("#bt_list").click(function(){
		location.href="/board/list";
	});
	
});
</script>
</head>
<body>

<h3>글 상세보기</h3>

<div class="container">
  <form>
  	<input type="hidden" name="board_idx" value="<%=board.getBoard_idx()%>">
  	
    <label for="fname">작성자</label>
    <input type="text" name="writer" value="<%=board.getWriter()%>">

    <label for="lname">제목</label>
    <input type="text" name="title" value="<%=board.getTitle()%>">

    <label for="subject">내용</label>
    <textarea id="content" name="content" placeholder="내용을 입력해주세요.." style="height:200px"><%=board.getContent() %>></textarea>
	<%for(int i=0; i<board.getBoardFileList().size();i++){ %>
	<%BoardFile boardFile=board.getBoardFileList().get(i);%>
	<input type="hidden" name="filename" value="<%=boardFile.getFilename()%>">
	<P>
		<img src="/static/data/<%=boardFile.getFilename()%>" width="150px">
	</P>
	<%} %>
	<input type="file" name="photo">
	<br>
	<input type="file" name="photo">
	<p>
    <input type="button" value="수정" id="bt_edit">
    <input type="button" value="삭제" id="bt_del">
    <input type="button" value="목록" id="bt_list">
  </form>
</div>

</body>
</html>










