$('.content').hover(
	function(a) {
		$(this).find('i').addClass('fa-shake');
	},
	function(a) {
		$(this).find('i').removeClass('fa-shake');
	}
);