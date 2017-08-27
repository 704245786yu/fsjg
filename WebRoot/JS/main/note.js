$(function(){
	var index = $(':hidden[name="index"]').val();
	if(index != ''){
		$('.menu_li li').css('color','black');
		$('.menu_li li[name="'+index+'"]').css('color','#00b8ef');
		showDiv(index);
	}
	
	$('.menu_li li').click(function(){
		$('.menu_li li').css('color','black');
		$(this).css('color','#00b8ef');
		var index = $(this).attr('name');
		showDiv(index);
	});
});


function showDiv(index){
	var $divs = $('#mainContent > div');
	$divs.css('display','none');
	$divs.filter("[name='"+index+"']").css('display','block');
}