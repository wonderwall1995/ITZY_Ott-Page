<%@page import="ITzy.OTT.dto.PpsDto"%>
<%@page import="ITzy.OTT.dto.MemberDto"%>
<%@page import="ITzy.OTT.util.Utility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    List<PpsDto> list = (List<PpsDto>)request.getAttribute("ppslist");
    int pagePps = (Integer)request.getAttribute("pagePps");
    int pageNumber = (Integer)request.getAttribute("pageNumber");
    String choice = (String)request.getAttribute("choice");
    String search = (String)request.getAttribute("search");
    %>
    
        	<%
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포트폴리오 페이지</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<!-- <script type="text/javascript" src="./jquery/jquery.twbsPagination.min.js"></script> -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>

<style type="text/css">
.table th, .table td {
	text-align: center;
	vertical-align: middle!important;
}
</style>

</head>
<body bgcolor="#e9e9e9">
<div align="center">
<h1>포트폴리오 프로필</h1>
<h5>※ 사진은 188.8 X 226.6 px로 치환됩니다 ※</h5>
</div>
<hr>
<div align="center">
	<table class="table table-hover table-sm" style="width: 1000px">
	<col width="70"><col width="100"><col width="300"><col width="70">
	<col width="70"><col width="80"><col width="100">

		<thead>
			<tr>
				<th>번호</th><th>작성자</th><th>성함</th><th>사진</th>
				<th>조회수</th><th>작성일</th><th>다운로드</th>				
			</tr>


		</thead>
		
		<tbody>
		
		<%
	if(list == null || list.size() == 0){
%>
		<tr>
			<td colspan="6">작성된 포트폴리오가 없습니다</td>
		</tr>
	<%
	}else {	
		for(int i = 0;i < list.size(); i++)
		{
		PpsDto pps = list.get(i);
	%>		
		
												
<%
			if(pps.getDel() == 0 ){		// 삭제 확인%>

			<tr>
				<th><%=i + 1 + (pageNumber * 10) %></th>			
				<td><%=pps.getId() %></td>	
				<td style="text-align: center;">				
					<%=Utility.arrow(pps.getDepth()) %>
					<a href="ppsdetail.do?seq=<%=pps.getSeq() %>">
					<%=pps.getTitle() %></a>
				</td>
				<td>
				<img src="upload/<%=pps.getNewfilename()%>" style="width:188.8px; height:226.6px;">
				</td>
				<td><%=pps.getReadcount() %></td>
				<td><%=pps.getRegdate() %></td>
				<td>
					<input type="button" value="다운로드" 
					onclick="filedown(<%=pps.getSeq()%>, '<%=pps.getNewfilename()%>', '<%=pps.getFilename()%>')">
				</td>
			</tr>	
	<%
			}else if(pps.getDel() == 1){
	%>				
			<tr>
				<td colspan="7">
					<%=Utility.arrow(pps.getDepth()) %>
					<font color="#ff0000">*** 이 글은 작성자에 의해서 삭제되었습니다 ***</font>
				</td>
			</tr>			
		<%
			}	
		%>
				
					
	<%	
		}
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
	<button type="button" onclick="ppsWrite()">프로필 등록</button>	
</div>

<!-- 파일다운 (controller -> downloadview) -->
<form name="file_down" action="ppfiledownload.do" method="post"> <!-- p 하나 더 -->
	<input type="hidden" name="newfilename">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>


<script type="text/javascript">
	function ppsWrite() {
		location.href = "ppswrite.do";
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