/**press 정보를 가져와 언론사 선택 리스트를 만들어주는 스크립트
 * seo
 */

// ajax로 서버에 저장된 press 정보를 가져옴
$.ajax({
	url: "/pressinfo",
	type: "get",
	dateType : "json", 
	success: function(response) {
		let pressList = JSON.parse(response);
		let pressContainer = $("#press-info");

		for (let i = 0; i * 4 < pressList.length; i++) {
			let li = $("<li class='press-row'>");
			let ul = $("<ul class='press-article'>");
			
			for (let j = 0; j < 4; j++) {
				let index = i * 4 + j;
				let p = pressList[index];

				let pressLi = $("<li>");
				let pressLabel = $("<label>");
				let pressCheckbox = $("<input type='checkbox' name='selectedPressKeys' value=" + p.press_key + ">");
				
				pressLabel.append(pressCheckbox);
				pressLabel.append(p.press_name);
				pressLi.append(pressLabel);
				ul.append(pressLi);
			}
			
			li.append(ul);
			pressContainer.append(li);
			
			limitPressCheckbox();
		};
	},
	error: function() {
		alert("press list를 불러오는 중에 오류가 발생했습니다.");
	}

});

// 언론사를 최대 limitPressCheckboxNum 만큼 선택할 수 있게 제한을 두는 함수 
function limitPressCheckbox() {
	// 최대 선택 가능한 개수
	let limitPressCheckboxNum = 10;
	
	$("input[name='selectedPressKeys']").change(function() {
		let selectedPressKeys = $("input[name='selectedPressKeys']:checked").length;
		
		if (selectedPressKeys > limitPressCheckboxNum) {
			alert("언론사는 " + limitPressCheckboxNum + "개 이하로 선택해 주세요.");
			
			$(this).prop("checked", false);
		}
	});
}