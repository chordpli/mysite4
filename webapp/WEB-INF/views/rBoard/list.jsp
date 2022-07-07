<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<!-- //aside -->
			<c:import url="/WEB-INF/views/includes/asideBoard.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="./list" method="get">
							<div class="form-group text-right">
								<input type="text" name = "keyword" value ="">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<c:forEach items="${rList.boardList }" var="rVo" >
								<tbody>
									<tr>
										<td>${rVo.no }</td>
										<td class="text-left"><a href="./read/${rVo.no }">${rVo.title }</a></td>
										<td>${rVo.userName }</td>
										<td>${rVo.hit }</td>
										<td>${rVo.regDate }</td>
										<c:if test="${ authUser.no eq rVo.userNo}">
											<td><a href="./delete/${rVo.no}">[삭제]</a></td>
										</c:if>
									</tr>
								</tbody>
							</c:forEach>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${rList.prev}">
									<li><a href="">◀</a></li>
								</c:if>
								
								<c:forEach begin="${rList.startPageBtnNo}" end="${rList.endPageBtnNo}" step="1" var="page">
									<c:choose>
										<c:when test ="${param.crtPage == page }" >
											<li class="active"><a href="./list?crtPage=${page}">${page }</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="./list?crtPage=${page}">${page }</a></li>
										</c:otherwise>
										
									</c:choose>
									
								</c:forEach>
								
								<c:if test = "${rList.next}">
								<li><a href="">▶</a></li>
								</c:if>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${authUser != null }">
							<a id="btn_write" href="./writeform/">글쓰기</a>
						</c:if>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
