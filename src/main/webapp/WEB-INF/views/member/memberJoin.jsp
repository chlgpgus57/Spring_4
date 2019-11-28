<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
	.a{
	color: green;
	}
	
	.b{
	color: red;
	}	
</style>
</head>

<body>
	<c:import url="../layout/nav.jsp" />
	<div class="container">
		<h2>Join</h2>
		<form action="memberJoin" method="post" id="form"  class="col-sm-8" enctype="multipart/form-data">

			<div class="form-group"  class="col-sm-4">
				<div>
				<label for="id">아이디:</label> 
				</div>
				<input type="text" class="form-control"
					id="id" name="id" >
				
				<p id="input_id" class="btn_check"></p>
			</div>
			
			<div class="form-group"  class="col-sm-4" >
				<label for="pw">비밀번호:</label> <input type="password"
					class="form-control" id="pw" name="pw">
				<p id="input_pw" class="btn_check"></p>
			</div>
			<div class="form-group"  class="col-sm-4">
				<label for="pwCheck">비밀번호 확인:</label> <input type="password"
					class="form-control" id="pwCheck" name="pwCheck">
				<p id="input_pwCheck" class="btn_check"></p>
			</div>
			<div class="form-group"  class="col-sm-4">
				<label for="name">이름:</label> <input type="text"
					class="form-control" id="name1" name="name">
				<p id="input_name1" class="btn_check"></p>
			</div>
			<div class="form-group"  class="col-sm-4">
				<label for="email">이메일:</label> <input type="email"
					class="form-control" id="email" name="email">
				<p id="input_email" class="btn_check"></p>
			</div>
			<div class="form-group"  class="col-sm-4">
				<label for="birth">생일:</label> <input type="date"
					class="form-control" id="phone" name="birth">
				<p id="input_phone" class="btn_check"></p>
			</div>
			<div class="form-group" class="col-sm-4">
				<label for="sel1">성별:</label>
				<select class="form-control" id="sel1" name="gender">
					<option value="N">성별</option>
					<option value="M">남성</option>
					<option value="F">여성</option>
				</select>
				<p id="input_gender" class="btn_check"></p>
			</div>
			
			<div class="form-group"  class="col-sm-4">
				<div>
				<label for="id">사진:</label> 
				</div>
				<input type="file" class="form-control"
					id="file" name="file" >
				
				<p id="input_file" class="btn_check"></p>
			</div>
			<input type="submit" id="join" class="btn btn-default" value="Join">
		</form>
	</div>

<script type="text/javascript">
$("#pwCheck").blur(function() {
	if($("#pw").val() != $(this).val()){
		alert('비밀번호가 일치하지 않습니다')
	}
})


	
$("#id").blur(function() {
	var id = $("#id").val();
	if($(this).val()!= ""){
		
	$.get("memberIdCheck?id="+id,function(data){
		
		if(data == 0){
			$("#input_id").html("중복된 ID입니다");
			$("#id").val("");
			$("#input_id").attr('class', 'b');
			$("#id").focus();
			idCheck=true;
			
		}else{
			$("#input_id").html("사용가능한 ID입니다");
			$("#input_id").attr('class', 'a');
			idCheck=false;
		}
	});
	}else{
		$("#input_id").html("ID를 입력하세요");
	}
});

var idCheck=false; //false : 중복된 ID, 또는 중복검사 안함
	//true : 사용가능한 ID
$("#join").click(function() {
	if($("#sel1").val() == "N"){
		$("#input_gender").html("성별을 선택하세요");
	};
	
});
</script>

</body>

</html>