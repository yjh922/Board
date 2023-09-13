<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: black;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
	background-color: #04AA6D;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
	background-color: #f1f1f1;
	text-align: center;
}
.id_input_re_1{
	color : green;
	display : none;
}
.id_input_re_2{
	color : red;
	display : none;
}
</style>
<%@ include file="./inc/head_link.jsp" %>
<script type="text/javascript">
function regist(){
	$("form").attr({
		action:"/admin/regist",
		method:"post"
	});
	$("form").submit();
}
$(function(){
	$("#bt_regist").click(function(){
		regist();
	});
	
});
$('.id_input').on("propertychange change keyup paste input", function(){
	//console.log("keyup 테스트");
	var id=$('.id_input').val();
	var data ={id:id}
	
	$.ajax({
		type : "post",
		url : "/admin/idCheck",
		data : data
	});
});
</script>
</head>
<body>

	<form>
		<div class="container">
			<h1>회원가입</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="email"><b>아이디(이메일)</b></label> 
			<input type="text" placeholder="아이디 입력" name="id" id="email" required>
			<span class="id_input_re_1">사용가능한 아이디입니다.</span>
			<span class="id_input_re_2">아이디가 이미 존재합니다.</span>

			<label for="psw"><b>비밀번호</b></label> 
			<input type="password" placeholder="비밀번호 입력" name="pass" id="psw" required> 
			<label for="psw-repeat"><b>비밀번호확인</b></label> 
			<input type="password" placeholder="비밀번호 확인" name="psw-repeat" id="psw-repeat" required>
			<label for="name"><b>이름</b></label> 
			<input type="text" placeholder="이름 입력" name="name" id="email" required>
			<label for="phone"><b>전화번호</b></label> 
			<input type="text" placeholder="폰번호 입력" name="phone" id="email" required>
			
			<hr>
			<p>
				By creating an account you agree to our <a href="#">Terms &
					Privacy</a>.
			</p>

			<button type="button" class="registerbtn" id="bt_regist">가입</button>
		</div>

		<div class="container signin">
			<p>
				Already have an account? <a href="#">Sign in</a>.
			</p>
		</div>
	</form>

</body>
</html>
