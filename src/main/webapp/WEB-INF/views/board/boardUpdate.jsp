<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../layout/bootstrap.jsp" />
</head>
<body>
<c:import url="../layout/nav.jsp" />
<c:import url="../layout/summernote.jsp" />
<div class="container">
<h2>Point Update Page</h2>

<form action="noticeUpdate.notice" method="post" enctype="multipart/form-data">
	<div class="form-group" hidden="">
     	 <label for="name">num:</label>
      	<input type="text" class="form-control" id="title" placeholder="Enter title" name="num" value="${dto.num}">
    </div>
	
	<div class="form-group">
     	 <label for="name">TITLE:</label>
      	<input type="text" class="form-control" id="title" placeholder="Enter title" name="title" value="${dto.title}">
    </div>
    
    <div class="form-group">
    	  <label for="kor">WRITER:</label>
      	<input type="text" class="form-control" id="writer" placeholder="Enter Your Name" name="writer" value="${dto.writer}" readonly="readonly">
    </div>
    
    <div class="form-group">
    	  <label for="eng">CONTENTS:</label>
    	  <div id="contents" class="form-control" ></div>
    </div>
    		<div id = "files">
			<div class="form-group" >
				<label class="control-label col-sm-2" for="title">FILES</label>
				<div class="col-sm-9">
				<input type="file" class="form-control" id="file" name="file">
				</div>
				<div class="col-sm-1">
				<input type="button" class="form-control btn btn-danger del_file" value="DEL">
				</div>
			</div>
		</div>
		
		<input type="button" class="btn btn-primary" value="ADD FILE" id="add_file">
    
    <div id="fore">
    	
    	
    	<c:forEach items="${dto.files}" var="file" varStatus="status">
    		<div id="f${file.fnum}">
	    		<p>${file.oname} <input type="button" value="del${status.count}" class="btn btn-danger del" id="${file.fnum}" title="${status.count}"></p>
    		</div>
		</c:forEach>
  
    	
    	
    </div>
    
    <button type="submit" class="btn btn-primary" style="float: right;">Submit1</button>
    <a href ="noticeSelect.notice?num=${dto.num}"><button type="button" class="btn btn-default" style="float: right;">Cancel</button></a>
</form>
</div>
	<script type="text/javascript">
		var files = $("#files").html();
		var number=${fn:length(dto.files)};
		console.log(number);
		
		$("#contents").summernote();
	
		$("#contents").summernote('code', '${dto.contents}');
		
		$("#files").empty();
	
		$(".del").click(function() {
			var fnum = $(this).attr('id')
			
				
			$.post("./fileDelete",{fnum:fnum}, function(data) {
				if(data>0){
					$("#f"+fnum).remove();	
					number--;
				}
			});
			
		});
		
		$("#add_file").click(function() {
			if (number < 5) {
			$("#files").append(files);
				number++;
			}else{
				alert("파일은 최대 5개까지 첨부가능합니다");
			}
		
		});
		
		$("#files").on('click' ,".del_file" ,function() {
			//$(this).parents(".form-group").remove();
			$(this).closest(".form-group").remove();
			number--;
		});
	</script>
</body>
</html>