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


/*placeSearch 에 관한 alert*/

/*채팅바*/
/*to top*/

let badgeEl = document.querySelector('header .badges');
let toTopEl = document.querySelector('#to-top');

window.addEventListener('scroll', _.throttle(function() {
	console.log(window.scrollY);
	if (window.scrollY > 500) {
		//배지 숨기기
		gsap.to(badgeEl, .6, {
			opacity: 0,
			display: 'none'
		});
		// 버튼 보이기
		gsap.to(toTopEl, .2, {
			x: 0
		});
	} else {
		//배지 보이기
		gsap.to(badgeEl, .6, {
			opacity: 1,
			display: 'flex'
		});
		// 버튼 숨기기
		gsap.to(toTopEl, .2, {
			x: 100
		});
	}
}, 300));
// _.throttle(함수,시간)
// 상단으로 가기

toTopEl.addEventListener('click', function() {
	gsap.to(window, .7, {
		scrollTo: 0
	})
});

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

/*DB*/

$.ajax({
	url: "/wordCloud",
	type: "get",
	dataType: "json",
	success: function(response) {
		console.log(response);
		let arr = response;
		for (let i = 0; i < arr.length; i++) {
			datas[i] = JSON.parse(arr[i]);
			console.log(datas[i]);
		}

	},
	error: function() {
		alert("정상적이지 않은 요청입니다. 다시 시도해주세요");
	}
});//ajax


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

function wordclouding(data, tab_id) {

	let chart = anychart.tagCloud(data);

	chart.container(tab_id);

	chart.hovered().fill("#333");
	chart.hovered().fontWeight(700);
	chart.textSpacing(5);
	chart.angles([0]);

	chart.listen("pointClick", function(e) {
		var url = "/search?keyword=" + e.point.get("x");
		//$(this).val() 
		//controller ->  
		window.open(url, "_self");
	});

	chart.draw();
}



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
/* 뉴스 자세히 보기 */ // 0208 category ajax 안으로 이관 했습니다. seo
/*$('.content-cover img').on('click', function() {
	location = "/news"
})
$('.content-cover .a').on('click', function() {
	location = "/news"
})*/

/*검색 글자 제한*/
let replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
let replaceNotFullKorean = /[ㅏ-ㅣ]/gi;

$('#searchBar').on("focusout", function() {
	let x = $(this).val();
	if (x.length > 0) {
		if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
			x = x.replace(replaceNotFullKorean, "");
			x = x.replace(replaceChar, "");
		}
		console.log(x);
	}
}).on("keyup",function() {
	$(this).val($(this).val().replace(replaceChar,""));
	/*$(this).val($(this).val().replace(replaceNotFullKorean,""));*/
})



/* 02-08 seo category ajax start*/
$.ajax({
	url: "/headline",
	type: "get",
	dataType: "json",
	success: function(response) {
		for (let i = 0; i < 6; i++) { // categoryIdx
			for (let j = 0; j < 5; j++) { // listIdx
				// categoryidx-listidx-(a|img|date|main|cont)
				let n = response[i][j]; // news
				// 인덱스 보정
				let categoryIdx = i + 1;
				let listIdx = j + 1;
				let idx = categoryIdx + '-' + listIdx;
				
				$('#' + idx + '-a').attr('href', 'news/' + n.news_key);
				$('#' + idx + '-img').attr('src',
					n.news_thumbnailuri2 == null ? n.news_thumbnailuri : n.news_thumbnailuri2);

				// Posted July 11, 2017
				let date = new Date(n.news_writendate);
				// 'ko-KR'로 해도 되는데 원본이 en-US로 되어있어서 en-US로 작성
				let formattedDate = date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
				console.log(formattedDate);
				$('#' + idx + '-date').text("Posted " + formattedDate);

				$('#' + idx + '-main').attr('href', 'news/' + n.news_key);
				$('#' + idx + '-main').text(n.news_title);
				$('#' + idx + '-main').css({
					'color': 'black',
					'text-decoration': 'none'
				});

				let summ = n.summ_content;
				summ = summ.length > 150 ? summ.slice(0, 150) + '...' : summ;
				$('#' + idx + '-cont').attr('href', 'news/' + n.news_key);
				$('#' + idx + '-cont').text(summ);
				$('#' + idx + '-cont').css({
					'color': 'black',
					'text-decoration': 'none'
				});

			}
		}
	},
	error: function() {
		alert("정상적이지 않은 요청입니다. 다시 시도해주세요");
	}
});

$('.tab-link').click(function() {
	$('.tab-link').css({
		'font-size': '20px',
		'color': '#5A6372'
	});
	$('.tab-link.current').css({
		'color': '#333',
		'font-size': '22px'
	});
});
/* category ajax end*/