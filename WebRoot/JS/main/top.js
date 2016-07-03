$(function(){
	var pageName = $('input[name="pageName"]').val();
	if(pageName=="home"){
		$('li[name="li-home"] a').text("服饰分类");
	}
	$('li[name="li-'+pageName+'"]').css("background-image","url('image/nav-orange.png')");
});