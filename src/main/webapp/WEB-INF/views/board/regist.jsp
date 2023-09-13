<%@ page contentType="text/html; charset=UTF-8"%>
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

function regist(){
	$("form").attr({
		action:"/board/regist",
		method:"post",
		enctype:"multipart/form-data"
	});
	$("form").submit();
}


$(function(){
	$("#content").summernote({
		 height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '내용을 입력해 주세요.'	//placeholder 설정
	});
	
	$("#bt_regist").click(function(){
		regist();
	});
	$("#bt_list").click(function(){
		location.href="/board/list";
	});
	
});
</script>
</head>
<body>
	<div class="topnav" id="myTopnav">
		<a href="#home" class="active">홈</a> 
		<a href="#news">게시판</a> 
		<a href="#contact">로그인</a> 
		<a href="#about">등록</a> 
		<a href="javascript:void(0);" class="icon" onclick="myFunction()"> 
			<i class="fa fa-bars"></i>
		</a>
	</div>
<h3>글 작성</h3>
<br>
<div class="container">
  <form>
    <label for="fname">작성자</label>
    <input type="text" name="writer" placeholder="작성자 ..">

    <label for="lname">제목</label>
    <input type="text" name="title" placeholder="제목을 입력해주세요..">

    <label for="subject">내용</label>
    <textarea id="content" name="content" placeholder="내용을 입력해주세요.." style="height:200px"></textarea>
	<input type="file" name="photo">
	<br>

	<p>
    <input type="button" value="등록" id="bt_regist">
    <input type="button" value="목록보기" id="bt_list">
  </form>
</div>

</body>
</html>
