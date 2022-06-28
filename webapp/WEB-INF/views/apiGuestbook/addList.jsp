<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="../../assets/js/jquery/jquery-1.12.4.js"></script>


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
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath}/api/guestbook/add" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5" name = "content"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit" id ="btnSubmit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
					</form>	
						<!-- 리스트 -->

						<div id = 'listArea'>
						
						</div>
						
						<!-- //리스트 -->
					<!-- //guestRead -->
					
				</div>
				<!-- //guestbook -->
			
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

<script type="text/javascript">

/* 준비가 끝나면 */
$(document).ready(function(){
	console.log("jquery 로 요청  data만 받는 요청");
	
	//리스트 요청 + 그리기
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},
		
		
		dataType : "json",
		success : function(gList){
			/*성공시 처리해야될 코드 작성*/
			console.log(gList);
			
			
			
			for(var i = 0; i < gList.length; i++){
				render(gList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});


/* 저장 버튼을 클릭 했을 때 */
$("#btnSubmit").on("click", function(){
	console.log("저장버튼 클릭");
	
	var name = $("[name = 'name']").val();
	var password = $("[name = 'password']").val();
	var content = 4("[name ='content']").val();
	
	var guestVo = {
			name: name,
			password: password,
			content: content
	}
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/add",		
		type : "post",
		//contentType : "application/json",
		//data : {name: name, password: password, content: conetent},
		data : guestVo,
		
		dataType : "json",
		success : function(gList){
			/*성공시 처리해야될 코드 작성*/
			console.log(gList);
			
			
			
			for(var i = 0; i < gList.length; i++){
				render(gList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});

 


 
 
 
 
 
/* 리스트 그리기 */
function render(guestbookVo){
	console.log("render()");
	
	var str = "";
	str += '<table class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>'+guestbookVo.no+'</td>';
	str += '		<td>'+guestbookVo.name+'</td>';
	str += '		<td>'+guestbookVo.regDate+'</td>';
	str += '		<td><a href="./deleteform/'+guestbookVo.no+'">[삭제]</a></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
	str += '</tr>';
	str += '</table>';
	
	$('#listArea').append(str);
}


</script>

</html>