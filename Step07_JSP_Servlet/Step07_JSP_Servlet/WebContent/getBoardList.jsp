<%@page import="com.samsung.biz.board.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
	ArrayList<BoardVO> boardList = 
		(ArrayList<BoardVO>)request.getAttribute("boardList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 목록</title>
</head>

<body>
	<center>
		<h1>글 목록</h1>
		<h3>
			<%=session.getAttribute("name") %>님 로그인 환영합니다...... 
				<a href="logout">Log-out</a>
		</h3>

		<!-- 검색 시작 -->
		<form action="getBoardList" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
					<select name="searchCondition">
							<option value="TITLE">제목
							<option value="CONTENT">내용
					</select> 
					<input name="searchKeyword" type="text" /> 
					<input type="submit" value="검색" /></td>
				</tr>
			</table>
		</form>
		<!-- 검색 종료 -->

		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="150">작성자</th>
				<th bgcolor="orange" width="150">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
<%
	//for(int i = 0; i < boardList.size(); i++){
	//	BoardVO board = boardList.get(i);
	//}
	for(BoardVO board : boardList){
		int seq = board.getSeq();
%>		
			<tr>
				<th width="100"><%=seq %></th>
				<th width="200"><a href="getBoard?seq=<%=board.getSeq()%>"><%=board.getTitle() %></a></th>
				<th width="150"><%=board.getNickname() %></th>
				<th width="150"><%=board.getRegdate() %></th>
				<th width="100"><%=board.getCnt() %></th>
			</tr>
<%
	}
%>		
		</table>
		<br> <a href="addBoard.jsp">새글 등록</a>
	</center>
</body>
</html>











