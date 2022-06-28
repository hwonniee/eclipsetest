//게시글 등록 시 validation check
function boardCheck() {
	if($("[name='name']").val().length == 0) {
		alert("작성자를 입력하세요.");
		return false;
	}
	if($("[name='pass']").val().length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}	
	if($("[name='title']").val().length == 0) {
		alert("제목을 입력하세요.");
		return false;
	}
	return true;
}

// 팝업창 열기
function open_win(url, name) {
	window.open(url, name, "width=500, height=230");
}

// 비밀번호 입력 check
function passCheck() {
	if($("[name='pass']").val().length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}