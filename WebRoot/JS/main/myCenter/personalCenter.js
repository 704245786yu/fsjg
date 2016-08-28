$('.menu_li li[class!="level1"]').click(function(){
	$('.menu_li li[class!="level1"]').css('color','black');
	$(this).css('color','#00b8ef');
	var index = Number($(this).attr('name'));
	var $divs = $('#mainContent > div');
	$divs.css('display','none');
	$divs[index-1].style.display = 'block';
});