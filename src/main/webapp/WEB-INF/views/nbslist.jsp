<%@page import="ITzy.OTT.util.Utility"%>
<%@page import="ITzy.OTT.dto.MemberDto"%>
<%@page import="ITzy.OTT.dto.NbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    List<NbsDto> list = (List<NbsDto>)request.getAttribute("nbslist");
    int npageNbs = (Integer)request.getAttribute("pageNbs");
    int npageNumber = (Integer)request.getAttribute("npageNumber");
    String nchoice = (String)request.getAttribute("nchoice");
    String nsearch = (String)request.getAttribute("nsearch");
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
<title>nbsList</title>

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

<h1>공고게시판</h1>
<hr>

<div align="center">
	<table class="table table-hover table-sm" style="width: 1000px">
	<col width="70"><col width="80"><col width="300"><col width="70">
	<col width="70"><col width="80"><col width="100">
		<thead>
			<tr>
				<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
				<th>조회수</th><th>다운로드 수</th><th>작성일</th>						
			</tr>
		</thead>
		
		<tbody>
		
		
<%
	if(list == null || list.size() == 0){
%>
		<tr>
			<td colspan="6">작성된 글이 없습니다</td>
		</tr>
	<%
	}else {	
		for(int i = 0;i < list.size(); i++)
		{
		NbsDto nbs = list.get(i);
	%>		
		
												
<%
			if(nbs.getDel() == 0 ){		// 삭제 확인%>
			
			<tr>
				<th><%=i + 1 + (npageNumber * 10) %></th>			
				<td><%=nbs.getId() %></td>	
				<td style="text-align: center;">				
					<%=Utility.arrow(nbs.getDepth()) %>
					<a href="nbsdetail.do?seq=<%=nbs.getSeq() %>">
					<%=nbs.getTitle() %></a>
				</td>
				<td>
					<input type="button" value="다운로드" 
					onclick="filedown(<%=nbs.getSeq()%>, '<%=nbs.getNewfilename()%>', '<%=nbs.getFilename()%>')">
				</td>
				<td><%=nbs.getReadcount() %></td>
				<td><%=nbs.getDowncount() %></td>
				<td><%=nbs.getRegdate() %></td>
			</tr>	
	<%
			}else if(nbs.getDel() == 1){
	%>				
			<tr>
				<td colspan="7">
					<%=Utility.arrow(nbs.getDepth()) %>
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
				<select class="custom-select" id="nchoice" name="nchoice">
					<option selected>검색</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option>
				</select>
			</td>
			<td style="padding-left: 5px" class="align-middle">
				<input type="text" class="form-control" id="nsearch" name="nsearch" placeholder="검색어" value="<%=nsearch %>">
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
	<button type="button" onclick="nbsWrite()">공고추가</button>	
</div>

<!-- 파일다운 (controller -> downloadview) -->
<form name="file_down" action="filedownload.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>


<script type="text/javascript">

	let nsearch = "<%=nsearch %>";
	console.log("nsearch = " + nsearch);
	if(nsearch != ""){
		let obj = document.getElementById("nchoice");
		obj.value = "<%=nchoice %>";
		obj.setAttribute("selected", "selected");
	}
	
	function searchBtn() {
		let nchoice = document.getElementById('nchoice').value;
		let nsearch = document.getElementById('nsearch').value;
		location.href = "nbslist.do?nchoice=" + nchoice + "&nsearch=" + nsearch;
	}
	
	$('#pagination').twbsPagination({
			startPage: <%=npageNumber+1 %>, 
		    totalPages: <%=npageNbs %>,
		    visiblePages: 10,
		    first:'<span srid-hidden="true">«</span>',
		    prev:"이전",
		    next:"다음",
		    last:'<span srid-hidden="true">»</span>',
		    initiateStartPageClick:false,   // onPageClick 자동실행되지 않도록
		    onPageClick: function (event, page) {
	        // alert(page);
	        let nchoice = document.getElementById('nchoice').value;
			let nsearch = document.getElementById('nsearch').value;
	    	location.href = "nbslist.do?nchoice=" + nchoice + "&nsearch=" + nsearch + "&npageNumber=" + (page-1);
	    }
	});
	
	
	function nbsWrite() {
		location.href = "nbswrite.do";
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