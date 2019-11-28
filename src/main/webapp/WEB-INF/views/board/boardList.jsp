<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:import url="../layout/bootstrap.jsp" />
<body>
	<c:import url="../layout/nav.jsp" />
	<div class="container">
	<h2>${board}</h2>
		<form  id="frm" action="${board}List">
			<input type="hidden" id="curPage" value= "1" name="curPage" >
			<select name="kind">
				<option id="kt" value="kt">Title</option>
				<option id="kw" value="kw">Writer</option>
				<option id="kc" value="kc">Contents</option>
			</select> <input type="text" name="search" value="${pager.search}">
			<button>검색</button>

		</form>


		<table class="table">
			<thead>
				<tr>
					<th class="col-lg-1" style="text-align: center;">NUM</th>
					<th class="col-lg-4" style="text-align: center;">TITLE</th>
					<th class="col-lg-1" style="text-align: center;">WRITER</th>
					<th class="col-lg-1" style="text-align: center;">DATE</th>
					<th class="col-lg-1" style="text-align: center;">HIT</th>
				</tr>

			</thead>
			<tbody>

				<c:forEach items="${list}" var="dto">
					<tr>
						<td style="text-align: center;">${dto.num}</td>
						<td style="text-align: left;">
						<c:catch>
							<c:forEach begin="1" end="${dto.depth}">&nbsp;&nbsp;ㄴ</c:forEach>
						</c:catch>
						<a href="${board}Select?num=${dto.num}">${dto.title}</a></td>
						<td style="text-align: center;">${dto.writer}</td>
						<td style="text-align: center;">${dto.reg_date}</td>
						<td style="text-align: center;">${dto.hit}</td>
					</tr>
				</c:forEach>



			</tbody>

		</table>
		<div style="margin: auto;">
			<ul class="pagination">
				<c:if test="${pager.curBlock gt 1}">
					<li><span class="list" id="${pager.startNum-1}">이전</span></li>
				</c:if>
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
					<li><span class="list" id="${i}">${i}</span></li>
				</c:forEach>
				<c:if test="${pager.curBlock ne pager.totalBlock}">
					<li><span class="list" id="${pager.lastNum+1}">다음</span></li>
				</c:if>
			</ul>
		</div>
		<!-- session member, memberDTO -->
		<c:if test="${not empty member}">
			<a href="${board}Write" class="btn btn-primary"
				style="float: right;">INSERT</a>
		</c:if>
		<c:choose>
			<c:when test=""></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</div>
	<script type="text/javascript">
	var kind = '${pager.kind}';
	if(kind==''){
		kind='kt';
	}
	$("#"+kind).prop("selected", true);
	
	$(".list").click(function() {
		$("#curPage").val($(this).attr("id"))
		$("#frm").submit();
	})
	</script>
</body>
</html>