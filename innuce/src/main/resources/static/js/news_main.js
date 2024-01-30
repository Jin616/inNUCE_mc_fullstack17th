$('#change ul.menu li').click(function() {
	var tab_id = $(this).attr('data-tab');

	$('#change ul.menu li').removeClass('current');
	$('#change .tab-content').removeClass('current');

	$(this).addClass('current');
	$("#" + tab_id).addClass('current');
});