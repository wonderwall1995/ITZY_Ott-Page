<%@page import="ITzy.OTT.dto.PdsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    List<PdsDto> list = (List<PdsDto>)request.getAttribute("pdslist");
    int pageBbs = (Integer)request.getAttribute("pagePds");
    int pageNumber = (Integer)request.getAttribute("pageNumber");
    String choice = (String)request.getAttribute("choice");
    String search = (String)request.getAttribute("search");
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script type="text/javascript" src="./jquery/jquery.twbsPagination.min.js"></script>
<style type="text/css">
.table th, .table td {
	text-align: center;
	vertical-align: middle!important;
}
</style>

</head>
<body bgcolor="#e9e9e9">

<h1>자료실</h1>
<hr>

<div align="center">
	<table class="table table-hover table-sm" style="width: 1000px">
	<col width="70"><col width="100"><col width="300"><col width="70">
	<col width="70"><col width="80"><col width="100">
		<thead>
			<tr>
				<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
				<th>조회수</th><th>다운로드 수</th><th>작성일</th>						
			</tr>
		</thead>
		
		<tbody>
<%
for(int i = 0; i < list.size(); i++){
	PdsDto pds = list.get(i);
%>
		
		<tr>
			<th><%=i + 1 %></th>
			<td><%=pds.getId() %></td>	
			<td>
				<a href="pdsdetail.do?seq=<%=pds.getSeq() %>"><%=pds.getTitle() %></a>
			</td>	
			<td>
				<input type="button" value="다운로드"
				onclick="filedown(<%=pds.getSeq()%>, '<%=pds.getNewfilename()%>', '<%=pds.getFilename()%>')">
			</td>
			<td><%=pds.getReadcount() %></td>
			<td><%=pds.getDowncount() %></td>
			<td><%=pds.getRegidate() %></td>
		</tr>	
	<%		
}
	%>
		</tbody>
	</table>
	<br>
	
	
	<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
		<tr>
			<td style="padding-left: 5px">
				<select class="custom-select" id="choice" name="choice">
					<option selected>검색</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option>
				</select>
			</td>
			<td style="padding-left: 5px" class="align-middle">
				<input type="text" class="form-control" id="search" name="search" placeholder="검색어" value="<%=search %>">
			<td style="padding-left: 5px">
				<span>
					<button type="button" class="btn btn-primary" onclick="searchBtn()">검색</button>
				</span>
			</td>
		</tr>
	</table>
	<br>
	
	<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination" style="justify-content:center"></ul>
    </nav>
	</div>
	
	
	
	<br>
	<button type="button" onclick="pdsWrite()">자료추가</button>	
</div>

<!-- 파일다운 (controller -> downloadview) -->
<form name="file_down" action="pfiledownload.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>


<script type="text/javascript">
	function pdsWrite() {
		location.href = "pdswrite.do";
	}
	function filedown(seq, newfilename, filename) {
		document.file_down.newfilename.value = newfilename;
		document.file_down.filename.value = filename;
		document.file_down.seq.value = seq;
		document.file_down.submit();
		setTimeout(replay, 500);
	}
	function replay() {
		location.reload();
	}
	
</script>

</body>
</html>