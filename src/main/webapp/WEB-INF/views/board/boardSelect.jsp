<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../layout/bootstrap.jsp" />
</head>
<body>
	<c:import url="../layout/nav.jsp" />
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th class="col-lg-6" style="text-align: center;">TITLE</th>
					<th class="col-lg-2" style="text-align: center;">WRITER</th>
					<th class="col-lg-2" style="text-align: center;">DATE</th>
					<th class="col-lg-2" style="text-align: center;">HIT</th>
				</tr>

			</thead>
			<tbody>

				<tr>

					<td style="text-align: center;">${boardVO.title}</td>
					<td style="text-align: center;">${boardVO.writer}</td>
					<td style="text-align: center;">${boardVO.reg_date}</td>
					<td style="text-align: center;">${boardVO.hit}</td>
				</tr>

			</tbody>

		</table>
	</div>
	<div class="container">
		<div class="jumbotron">
			<h4>${boardVO.contents}</h4>
		</div>
		
		<div>
			
			<c:catch>
			<c:forEach items="${boardVO.files}" var="file">
				<a href="./fileDown?fnum=${file.fnum}">${file.oname}</a>
			</c:forEach>
			</c:catch>
			
		</div>
		
		<div>
			<ul class="pager">
				<li><a href="noticeSelect.jsp?num=${nump}" id="pre">Previous</a></li>
				<li><a href="noticeSelect.jsp?num=${numn}" id="next">Next</a></li>
			</ul>
			<div style="float: left;">
				<a href="${board}List" class="btn btn-success">List</a>
			</div>
			<div style="width: 213px; height: 33px; float: right;">

				<c:if test="${member.id eq boardVO.writer}">
					<a href="" class="btn btn-danger" data-toggle="modal"
					data-target="#myModal" style="float: right; margin-left: 3px;">Delete</a>
				</c:if>

				<c:if test="${board ne 'notice'}">
				<a href="qnaReply?num=${boardVO.num}" class="btn btn-info" style="float: right; margin-left: 3px;">Reply</a>
				</c:if>
				
				<c:if test="${member.id eq boardVO.writer}">
					<a href="${board}Update?num=${boardVO.num}" class="btn btn-primary" style="float: right; margin-left: 3px;">Update</a>
				</c:if>

				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">삭제 확인</h4>
							</div>
							<div class="modal-body">
								<p>정말 삭제하시겠습니까?</p>
							</div>
							<div class="modal-footer">
								<button id="btn" type="button" class="btn btn-danger"
									data-dismiss="modal">예</button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">아니오</button>
							</div>
						</div>

					</div>
				</div>

			</div>

		</div>

	</div>

	<script type="text/javascript">
$("#btn").click(function() {
	location.href="${board}Delete?num=${dto.num}"
});


</script>

</body>
</html>