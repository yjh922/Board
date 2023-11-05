
<%@page import="org.sp.board.domain.Dog"%>
<%@page import="org.sp.board.domain.Admin"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Dog dog = (Dog)request.getAttribute("dog");
	Admin admin = (Admin) session.getAttribute("admin");
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
  float: right;
  margin-left: 10px;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
h3{
	margin-left:200px;
}
</style>
	<%@ include file="../inc/header_link.jsp"%>
<script type="text/javascript">


$(function(){
	$("#content").summernote({
		 height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  
	});
	
	$("#bt_ok").click(function(){
		if(confirm("예약 확정하시겠습니까?")){
			$("form").attr({
				action:"/admin/book/ok",
				method:"post"
			});
			$("form").submit();
		}
	});
	$("#bt_cancle").click(function(){
		if(confirm("예약 취소하시겠어요?")){
			//삭제 요청시 form 태그 안에 작성된 파라미터들을 한꺼번에 전송하자
			$("form").attr({
				action:"/admin/book/cancle",
				method:"post"
			});
			$("form").submit();
			
		}
	});
	$("#bt_list").click(function(){
		location.href="/admin/book/list";
	});
	
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
<h3>글 상세보기</h3>
<br>
<div class="container">
  <form>
  	<input type="hidden" name="dog_idx" value="<%=dog.getDog_idx()%>">
  	
    <label for="fname">반려견 이름</label>
    <input type="text" name="name" value="<%=dog.getName()%>" readonly>

    <label for="lname">보호자 전화번호</label>
    <input type="text" name="phone" value="<%=dog.getPhone()%>" readonly>
    
    <label for="lname">예약 날짜</label>
    <input type="text" name="bookdate" value="<%=dog.getBookdate()%>" readonly>
    
    <label for="lname">예약 시간</label>
    <input type="text" name="booktime" value="<%=dog.getBooktime()%>" readonly>

    <label for="subject">요구사항</label>
    <textarea id="content" name="content" style="height:200px"><%=dog.getContent() %></textarea>
	
	
	<p>
    <input type="button" value="예약 확정" id="bt_ok">
    <input type="button" value="예약 취소" id="bt_cancle">
    <input type="button" value="예약 목록" id="bt_list">
   
  </form>
</div>

</body>
</html>










