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
</head>
<body>
 <c:import url="../layout/nav.jsp" />
<div class = "container">
<h1>My Page</h1>
<form  method="post" id="frm" action="memberUpdate" >
	<div class="form-group" >
     	 <label for="id">ID:</label>
      	<input type="text" class="form-control" id="ID" readonly="readonly" name="id" value="${member.id}">
    </div>
    
    <div class="form-group" hidden="">
    	  <label for="pw">PASSWORD:</label>
      	<input type="password" class="form-control" id="pw" readonly="readonly" name="pw" value="${member.pw}">
    </div>
    
    <div class="form-group">
    	  <label for="name">NAME:</label>
      	<input type="text" class="form-control" id="name"  readonly="readonly" name="name" value="${member.name}">
    </div>
    <div class="form-group">
    	  <label for="email">EMAIL:</label>
      	<input type="text" class="form-control" id="email"  readonly="readonly" name="email" value="${member.email}">
    </div>
    <div class="form-group" class="col-sm-4">
				<label for="sel1">GENDER:</label>
				<select class="form-control" id="sel1" name="gender"  readonly="readonly">
				<c:if test="${member.gender eq 'M'}">
					<option value="M" selected="selected">남성</option>
					<option value="F">여성</option>
				</c:if>
				
				<c:if test="${member.gender eq 'F'}">
					<option value="M">남성</option>
					<option value="F" selected="selected">여성</option>
				</c:if>
				</select>
			</div>
    <div class="form-group">
		<label for="birth">BIRTH:</label> 
		<input type="date" class="form-control" id="birth"  name="birth"  readonly="readonly" value="${member.birth}">
				
	</div>
    <div class="form-group">
    	  <label for="kor">POINT:</label>
      	<input type="text" class="form-control" id="point" readonly="readonly" name="grade" value="${member.point}">
    </div>
    <img alt="" src="../resources/upload/member/${member.filename}">
    
    <a href = "memberDelete"><input type="button" class="btn btn-danger" id="submit" value="Delete" style="float: right;"></a>
    <a href = "memberPwUpdate"><input type="button" class="btn btn-sucess" id="submit" value="UpdatePW" style="float: right;"></a>
    <a href = "memberUpdate"><input type="button" class="btn btn-primary" id="submit" value="Update" style="float: right;"></a>
</form>
</div>
</body>
</html>