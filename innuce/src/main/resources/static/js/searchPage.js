
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


let nav= document.getElementsByTagName('nav');

$('#earth').click(function() {
    $(nav).toggle();
})




	$('#myPlace').on('click',function() {
		let check=confirm("위치 정보를 허용하시겠습니까?");
		 if(check) {
			location="/myLocation";
		 } else {
			 
		 }
	})


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
		console.log($(this).val(x));
	}
}).on("keyup",function() {
	$(this).val($(this).val().replace(replaceChar,""));
	/*$(this).val($(this).val().replace(replaceNotFullKorean,""));*/
})










