<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<c:import url="./layout/bootstrap.jsp"></c:import>
</head>

<body>
<c:import url="./layout/nav.jsp"></c:import>
<c:import url="./layout/summernote.jsp"></c:import>
<h1>
	Hello world! ${dto}
</h1>

<P id="p">  The time on the server is ${serverTime}. </P>

<button id="btn">Get Json1</button>
<button id="btn2">환율정보</button>
<button id="btn3">clickMovie</button>
<script type="text/javascript">

	$("#btn3").click(function() {
			
			$.ajax({
				
				type:"GET",
				url:"https://yts.lt/api/v2/list_movies.json",
				data:{
					title: "Hustlers",
					background_image:"https:\/\/yts.lt\/assets\/images\/movies\/hustlers_2019\/background.jpg"
				},
				success:function(data){
					console.log(data.title[1]);
					console.log(data.background_image[1]);
				}
			});
		});

	
	$("#btn2").click(function() {
		
		
		$.ajax({
			
			type:"GET",
			url:"https://api.manana.kr/exchange/price.json",
			data:{
				base:"USD",
				price:1,
				code:"KRW"
			},
			success:function(data){
				console.log(data.KRW);
			}
			
		});
		
	});




	$("#btn").click(function() {
		
		$.get("./getJson3", function(data) {
			
/* 			data=data.trim();
		 	데이터를 제이슨으로 바꿔달라는 코드 
			data= JSON.parse(data);
			alert(typeof data);
			alert("title:"+data.title+",writer:"+data.writer+",contents:"+data.contents);  */
			
			$.each(data, function(i,vo) {
				console.log(i);
				console.log(vo.num);
			});
			
			
/* 			for(var i=0;i<data.length;i++){
				console.log(data[i].num);
			} */
			
		});
		
	});
	

</script>
</body>
</html>
