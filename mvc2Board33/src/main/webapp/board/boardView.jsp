<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script src="script/jquery-3.6.0.js"></script>
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<div id="warp" align="center">
		<h1>게시글 상세보기</h1>
		<table>
			<tr>
				<th>작성자</th>
				<td>${board.name }</td>
				<th>이메일</th>
				<td>${board.email }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.writedate }"/></td>
				<th>조회수</th>
				<td>${board.readcount }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">${board.title }</td>
			</tr>
		</table>
		<br><br>
		<input type="button" value="수정" 
		onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'update')">
		<input type="button" value="삭제" 
		onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'delete')">
		<input type="button" value="목록" 
		onclick="location.href='BoardServlet?command=board_list&currPage=${currPage}&searchType=${searchVO.searchType }
		&searchText=${searchVO.searchText }'">
		<input type="button" value="등록" 
		onclick="location.href='BoardServlet?command=board_write_form'">
	</div>
</body>
</html>







