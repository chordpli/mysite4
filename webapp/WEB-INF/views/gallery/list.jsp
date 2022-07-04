<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->
		<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/Asidegallery.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
						<c:if test="${authUser != null }">
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						<div class="clear"></div>
						
			
					<ul id="viewArea" data-auth="${authUser.no}">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${gList}" var="gVo">
							<li>
								<div class="view" id = "t${gVo.no}" data-no="${gVo.no}" data-user = "${gVo.userNo}" data-save="${gVo.saveName }" data-content="${gVo.content }" >
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${gVo.saveName}">
									<div class="imgWriter">작성자: <strong>${gVo.userName}</strong></div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<input type="text" name = "userNo" value = "${authUser.no}">
						<button type="submit" class="btn" id="btnUpload">등록</button>
						
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
					<input type="hidden" name = "no" id = "imgNo">
					
				</div>
				<form method="" action="">
				<div class="modal-footer">
					<button type="button" id = "preDel" class="btn btn-default" data-dismiss="modal">닫기</button>
					<span class = "change"></span>
				</div>
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	

</body>

<script type="text/javascript">
	



$('#btnImgUpload').on("click", function(){
	console.log("이미지 등록 버튼 클릭");
	$('#addModal').modal("show");
});



$('#viewArea').on("click",".view", function(){
	console.log("이미지 클릭")
	var $this = $(this);
	var no= $this.data("no");
	var user = $this.data("user");
	var saveName= $this.data("save");
	var content = $this.data("content");
	var auth = data("auth");
	
	var adr = "${pageContext.request.contextPath}/upload/" + saveName;
	
	if(user == auth){
		$('.change').html('<button type="button" class="btn btn-danger" id="btnDel">삭제</button>');
	}
	
	console.log("게시판제작유저 : " + user);
	console.log("로그인유저 : " + auth);
	$('#user').val(user);
	$('#imgNo').val(no);
	console.log($('#imgNo').val());
	
	$('#viewModelImg').attr('src', adr);
	$('#viewModelContent').text(content);
	$('#delUserNo').val(user);
	$('#viewModal').modal("show");
});

/* 모달창 삭제버튼 클릭할 때 */
$(".modal-footer").one("click", "#btnDel", function(){
	console.log("모달창 삭제버튼 클릭");
	
	// 데이터 모으기
	
	var no = $('.modal-body [name = "no"]').val();
	
	console.log(no);
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/gallery/delete",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(no),
		
		dataType : "json",
		success : function(result){
			//성공시 처리해야될 코드 작성
			console.log(result);
			
			//성공이면 지우고
			if(result == "success"){
				$("#t"+no).remove();
				$("#viewModal").modal("hide");
			
			}else {
				alert("비밀번호를 확인하세요");
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	}); 
	//성공이면 리스트에서 제거하기
	
	//모달창 닫기
	
	
	
});

</script>



</html>

