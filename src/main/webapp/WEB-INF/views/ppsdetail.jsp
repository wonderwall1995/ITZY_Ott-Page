<%@page import="ITzy.OTT.dto.PpsDto"%>
<%@page import="ITzy.OTT.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
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
  	}
  	
  	pre{
  		white-space: pre-wrap;
  		word-break: break-all;
  		overflow: auto;
  	}  	
  </style>
</head>
<body>

<%
	MemberDto login = (MemberDto)session.getAttribute("login");
	PpsDto pps = (PpsDto)request.getAttribute("pps");
		if(login == null){
%>
		<script>
		alert('로그인 해 주십시오');
		location.href = "login.do";
		</script>
	<%
	}	
	%>  

<h1>자세히 보기</h1>
<hr>


<div id="app" class="container">

	<table class="table table-striped">
	<colgroup>
		<col style="width: 150px"/>
		<col style="width: 500px"/>
	</colgroup>
	
	<tr>
		<td colspan="2" style="font-size: 22px; font-weight: bold;"><%=pps.getTitle() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=pps.getId() %></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<input type="button" value="<%=pps.getFilename() %>">
		</td>
	<tr>
		<th>등록일</th>
		<td><%=pps.getRegdate() %></td>
	</tr>
	<tr>
		<th>조회수</th>
		<td><%=pps.getReadcount() %></td>
	</tr>
	<tr>
		<th>총 다운로드</th>
		<td><%=pps.getDowncount() %></td>
	</tr>
	
	<tr>
		<td colspan="2" style="background-color: white;">
			<pre style="font-size: 20px; font-family: 고딕, arial; background-color: white;"><%=pps.getContent() %></pre>
		</td>
	</tr>
	</table>
	
	<br>
	<!-- 답글 보류 -->
	<button type="button" class="btn btn-primary" onclick="location.href='ppslist.do'">목록</button>


<%
		if(pps.getId().equals(login.getId())){
%>	
	<button type="button" class="btn btn-primary" onclick="updatePps(<%=pps.getSeq() %>)">수정</button>	
	<button type="button" class="btn btn-primary" onclick="deletePps(<%=pps.getSeq() %>)">삭제</button>
	<%
		}
	%>

</div>
	<form name="file_down" action="filedownload.do" method="post">
		<input type="hidden" name="newfilename">
		<input type="hidden" name="filename">
		<input type="hidden" name="seq">
	</form>
<br><br>

<script type="text/javascript">
	function filedown(seq, newfilename, filename) {
		document.file_down.newfilename.value = newfilename;
		document.file_down.filename.value = filename;
		document.file_down.seq.value = seq;
		document.file_down.submit();
	}
	function updatePps( seq ) {
		location.href = "ppsupdate.do?seq=" + seq;
	}
	function deletePps( seq ) {
		location.href = "ppsdeleteAf.do?seq=" + seq;  
	}
</script>


<!-- 댓글  -->





</body>
</html>



