/*북마크 클릭 시*/
let flag2 = false;

$("main i").click(function() {
	flag2 = !flag2;

	if (!flag2) {
		$(this).removeClass('checked');
	} else {
		$(this).addClass('checked');
	}
})

/*채팅바*/

let badgeEl = document.querySelector('header .badges');

window.addEventListener('scroll', _.throttle(function() {
	console.log(window.scrollY);
	if (window.scrollY > 1000) {
		//배지 숨기기
		gsap.to(badgeEl, .6, {
			opacity: 0,
			display: 'none'
		});
	} else {
		//배지 보이기
		gsap.to(badgeEl, .6, {
			opacity: 1,
			display: 'block'
		});
	}
}, 300));
// _.throttle(함수,시간)

/*키워드*/
$('#keyword ul.menu li').click(function() {
	var tab_id = $(this).attr('data-tab');

	$('#keyword ul.menu li').removeClass('current');
	$('#keyword .tab-content').removeClass('current');

	$(this).addClass('current');
	$("#" + tab_id).addClass('current');
})
/*카테고리*/
$('#category ul.menu li').click(function() {
	var tab_id = $(this).attr('data-tab');

	$('#category ul.menu li').removeClass('current');
	$('#category .tab-content').removeClass('current');

	$(this).addClass('current');
	$("#" + tab_id).addClass('current');
})

/*wordcloud-category*/
let datas = new Array(6);
let flag = false;




/*for (let i = 0; i < datas.length; i++) {
	(function(i) {
		$.ajax({
			url: "/wordCloud",
			data: { "num": i },
			type: "get",
			dataType: "json",
			success: function(response) {
				if (i == 0) {
					datas[i] = JSON.parse(JSON.stringify(response));
				} else if (i == 1) {
					datas[i] = JSON.parse(JSON.stringify(response));
				} else if (i == 2) {
					datas[i] = JSON.parse(JSON.stringify(response));
				} else if (i == 3) {
					datas[i] = JSON.parse(JSON.stringify(response));
				} else if (i == 4) {
					datas[i] = JSON.parse(JSON.stringify(response));
				} else {
					datas[i] = JSON.parse(JSON.stringify(response));
				}

			},
			error: function() {
				alert("정상적이지 않은 요청입니다. 다시 시도해주세요");
				location.reload();
			}
		});//ajax

	})(i);
}
*/



/*	let tab_0 = {"politics":$('#politics').attr('value')};
	let tab_1 = {"economy":$('#economy').attr('value')};
	let tab_2 = {"society":$('#society').attr('value')};
	let tab_3 = {"life":$('#life').attr('value')};
	let tab_4 = {"world":$('#world').attr('value')};
	let tab_5 = {"edit-col":$('#edit-col').attr('value')};*/






$('.word-container .category-container .category-button div').click(function() {

	$('#choice').css('display', 'none');

	let tab_id = $(this).attr('data-tab');
	let num = parseInt(tab_id.slice(-1), 10);

	$('.word-container .category-container .category-button div').removeClass('current');
	$('.word-container .main-container .tab-container').removeClass('current');

	$(this).addClass('current');
	$("#" + tab_id).addClass('current');


	if (flag) {
		$("#" + tab_id).empty();
	}
	flag = true;
	wordclouding(datas[num], tab_id);


});

/*function wordclouding(data, tab_id) {

	let chart = anychart.tagCloud(data);

	chart.container(tab_id);

	chart.hovered().fill("#333");
	chart.hovered().fontWeight(700);
	chart.textSpacing(5);
	chart.angles([0]);

	chart.listen("pointClick", function(e) {
		var url = "//localhost:9079/search?keyword=" + e.point.get("x");
		//$(this).val() 
		//controller ->  
		window.open(url, "_self");
	});

	chart.draw();
}*/



/*검색옵션*/
let nav = document.getElementsByTagName('nav');
$('#earth').click(function() {
	$(nav).toggle();
})

/*위치 키워드 검색*/
$('#myPlace').on('click', function() {
	let check = confirm("위치 정보를 허용하시겠습니까?");
	if (check) {
		location = "/myLocation";
	} else {

	}
})
/* 뉴스 자세히 보기 */
$('.content-cover img').on('click', function() {
	location = "/news"
})
$('.content-cover .a').on('click', function() {
	location = "/news"
})

/*검색 글자 제한*/
let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}]/gi;
let replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;

$('#keyword').on("focusout", function() {
	let x = $(this).val();
	if (x.length > 0) {
		if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
			x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
		}
		$(this).val(x);
	}
}).on("keyup",function() {
	$(this).val($(this).val().replace(replaceChar,""));
})

