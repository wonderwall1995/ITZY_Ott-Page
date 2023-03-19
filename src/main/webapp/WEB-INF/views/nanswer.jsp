<%@page import="ITzy.OTT.dto.MemberDto"%>
<%@page import="ITzy.OTT.dto.NbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nbs answer</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<style type="text/css">
	th{
		background-color: #007bff;
		color: white;
		text-align: center;
		vertical-align: middle;
	}
	 input {
        padding: 5px;
        margin-bottom: 5px;
        outline: none;
      }
	</style>

</head>
<body>

<%
	NbsDto dto = (NbsDto)request.getAttribute("dto");
	MemberDto login = (MemberDto)session.getAttribute("login");
	if(login == null){
%>
	<script>
		alert('로그인 해 주십시오');
		location.href = "login.do";
	</script>
	<%
		}
	%>

<div id="app" class="container">

<h2>게시글</h2>

<table class="table table-sm">
<col width="100px"><col width="500px">
<tr>
	<th>작성자</th>
	<td><%=dto.getId() %></td>
</tr>
<tr>
	<th>제목</th>
	<td style="font-size: 20px;"><%=dto.getTitle() %></td>
</tr>
<tr>
	<th>작성일</th>
	<td><%=dto.getRegdate() %></td>
</tr>
<tr>
	<th>조회수</th>
	<td><%=dto.getReadcount() %></td>
</tr>
<tr>
	<td colspan="2" style="background-color: white;">
		<pre style="font-size: 20px;font-family: 고딕, arial;background-color: white"><%=dto.getContent() %></pre>
	</td>
</tr>
</table>



<h2>답글</h2>

<form action="nanswerAf.do" method="post">
<input type="hidden" name="seq" value="<%=dto.getSeq() %>">

<table class="table table-sm">
<col width="100px"><col width="500px">
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
	<td colspan="2">
		<textarea rows="18" id="content" name="content" class="form-control" placeholder="내용기입"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="작성완료">
	</td>
</tr>
</table>
</form>

</div>


</body>
</html>



 