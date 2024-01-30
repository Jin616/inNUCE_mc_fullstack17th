var badgeEl = document.querySelector('header .badges');


/*북마크 클릭 시*/
let flag2 = false;

$("main i").click(function() {
	flag2 = !flag2;
	
	if(!flag2) {
		$(this).removeClass('checked');
	} else {
		$(this).addClass('checked');
	}
})


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
/*$('#keyword .tab-content').click(function() {
	var tab_id = $(this).attr('data-tab');

	$('#keyword ul.menu li').removeClass('current');
	$('#keyword .tab-content').removeClass('current');

	$(this).addClass('current');
	$("#" + tab_id).addClass('current');
})
*/


let nav= document.getElementsByTagName('nav');

$('#earth').click(function() {
    $(nav).toggle();
})


/*내 위치검색 아이콘 클릭 시*/
/*let myPlace = confirm("내 위치정보를 허용하시겠습니까?");
if(myPlace==true) {
	
}*/


	$('#myPlace').on('click',function() {
		let check=confirm("위치 정보를 허용하시겠습니까?");
		 if(check) {
			location="/myLocation";
		 } else {
			 
		 }
	})

/*let json=JSON.parse('${myLocation}');
let country=json.geoLocation.country;

if(country=='KR') {
	document.getElementById("keyword").innerHTML=json.geoLocation.r1 + " " + json.geoLocation.r2 + " "+json.geoLocation.r3;
	
} else {
	document.getElementById("keyword").innerHTML="한국이 아닙니다.";
}*/






















