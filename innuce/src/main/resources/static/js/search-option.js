// form에 hidden 속성으로 값 입력
function searchOptionCheck() {
	// 언론사 체크 리스트 가져옴
	let checkedPressList =  $("input[name='selectedPressKeys']:checked");
	// form 안의 히든 속성으로 값을 넘겨줌
	let pressString = $("#pressString");
	let pressList = "";
	for (let i = 0; i < checkedPressList.length; i++) {
		 pressList += checkedPressList.eq(i).val();
		 
		 if (i != checkedPressList.length - 1)
		 	pressList += ","
	}
	
	// 언론사 값이 없다면 url에 노출되지 않도록 name 속성 제거
	if (pressList != "")
		pressString.attr("value", pressList);
	else
		pressString.removeAttr("name");	
	// 날짜 값
	let userSelectStart = $('#SD').val();
	let userSelectEnd = $('#ED').val();
	
	if (userSelectStart != "" & userSelectEnd != "") {
		$('#ds').attr("value", userSelectStart);
		$('#de').attr("value", userSelectEnd);
	} else {
		$('#ds').removeAttr("name");
		$('#de').removeAttr("name");
	}
	
	if ($('#pageNum').val() == "") {
		$('#pageNum').removeAttr("name");
	}
}

// 날짜 검증
$("input[type='date']").on('change', function() {
	let today = new Date();
	
	let sd = $('#SD');
	let ed = $('#ED');
	
	// 선택된 날짜 검증
	if (new Date($(this).val()) > today) {
		alert("검색 날짜(" + $(this).val() + ")로 미래를 선택할 수 없습니다.");
		$(this).val("");
		return ;
	} else if (new Date(sd.val()) > new Date(ed.val())) {
		alert("검색 시작 날짜(" + sd.val() + ")와 검색 종료 날짜(" + ed.val() + ")를 확인해주세요.");
		$(this).val("");
		return ;
	};
});

// parameter 넘어온 값 유지
function submitForm(i) {
	$('#pageNum').val(i);
	$('#searchForm').submit();
}