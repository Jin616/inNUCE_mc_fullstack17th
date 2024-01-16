const badgeEl = document.querySelector('header .badges');

window.addEventListener('scroll', _.throttle(function() {
	console.log(window.scrollY);
	if (window.scrollY > 800) {
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

$(document).ready(function() {
	$('#keyword ul.menu li').click(function() {
		var tab_id = $(this).attr('data-tab');
		
		$('#keyword ul.menu li').removeClass('current');
		$('#keyword .tab-content').removeClass('current');
		
		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})
});

$(document).ready(function() {
	$('#category ul.menu li').click(function() {
		var tab_id = $(this).attr('data-tab');
		
		$('#category ul.menu li').removeClass('current');
		$('#category .tab-content').removeClass('current');
		
		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})
});