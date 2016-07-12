$(function(){
	var pageName = $('input[name="pageName"]').val();
	if(pageName=="home"){
		$('li[name="li-home"] a').text("服饰分类");
	}
	$('li[name="li-'+pageName+'"]').css("background-image","url('image/nav-orange.png')");
	
	//通用头部搜索切换
    $('#search-hd .search-input').on('input propertychange',function(){
        var val = $(this).val();
        if(val.length > 0){
            $('#search-hd .pholder').hide(0);
        }else{
            var index = $('#search-bd li.selected').index();
            $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
        }
    })
    $('#search-bd li').click(function(){
        var index = $(this).index();
        $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
        $('#search-hd .search-input').eq(index).show().siblings('.search-input').hide(0);
        $(this).addClass('selected').siblings().removeClass('selected');
        $('#search-hd .search-input').val('');
    });
});

$('#globalSearchForm').submit(function(e){
	var ary = ['indent', 'enterprise', 'sample'];
	var $form = $(e.target);
	var $inputs = $form.find('input');
	for(var i=0; i<$inputs.length; i++){
		var $input = $($inputs[i]);
		if($input.css('display') != 'none'){
			var action = ary[i]+'/search'; 
			$form.attr('action',action);
		}
	}
});