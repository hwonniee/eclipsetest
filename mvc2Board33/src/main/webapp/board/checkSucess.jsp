<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<script type="text/javascript">
	if(window.name == "update") {
		// 팝업창을 띄운 부모페이지를 이동시킴
		window.opener.parent.location.href = 
			"BoardServlet?command=board_update_form&num=${param.num}";
	} else if (window.name == "delete") {
		alert('삭제되었습니다.');
		// 팝업창을 띄운 부모페이지를 이동시킴
		window.opener.parent.location.href = 
			"BoardServlet?command=board_delete&num=${param.num}";
	}
	window.close();
</script>
</body>
</html>