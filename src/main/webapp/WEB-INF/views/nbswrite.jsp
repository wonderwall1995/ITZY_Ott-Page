<%@page import="ITzy.OTT.dto.NbsDto"%>
<%@page import="ITzy.OTT.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
	MemberDto login = (MemberDto)session.getAttribute("login");
	NbsDto nbs = (NbsDto)request.getAttribute("nbs");
		if(login == null){
%>
		<script>
		alert('로그인 해 주십시오');
		location.href = "login.do";
		</script>
	<%
	}	
	%>  	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	
	<style type="text/css">
	th{
		background-color: #007bff;
		color: white;
		text-align: center;
		vertical-align: middle;
	}
	</style>
</head>
<body>
	<h1>공고 올리기</h1>
	<hr>

<div id="app" class="container">
	<form action="nbsupload.do" method="post" id="frm" enctype="multipart/form-data">
		<table class="table table-sm">
			<tr>
				<th>아이디</th>
				<td>
					<%=login.getId() %>
					<input type="hidden" name="id" value="<%=login.getId() %>">
				</td>
			</tr>
			
			<tr>
				<th class="align-middle">제목</th>
				<td>
					<input type="text" id="title" name="title" size="50px" class="form-control form-control-lg" placeholder="제목기입">
				</td>
			</tr>
			
			<tr>
				<th>파일업로드</th>
				<td>
					<input type="file" name="fileload">
				</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="18" id="content" name="content" class="form-control" placeholder="내용기입"></textarea>
				</td>
			</tr>
			
			<tr>				
				<td colspan="2" align="right" style="padding-top: 20px">
					<button type="submit" class="btn btn-primary">업로드</button>
					
				</td>
			</tr>
						
		</table>
	</form>
</div>


	<script type="text/javascript">
	$(document).ready(function() {
		
		$("button").click(function() {
			
			if($("#title").val().trim() == "" ){
				alert("제목을 기입해 주십시오");
				return;
			}else if($("#content").val().trim() == "" ){
				alert("내용을 기입해 주십시오");
				return;
			}else{
				$("#frm").submit();
			}		
		});	
	});
	</script>


</body>
</html>




