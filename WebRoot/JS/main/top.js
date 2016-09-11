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
//        $('#search-hd .search-input').val('');
    });
    
    //设置当前搜索框的搜索状态
    var tabIndex = $('.banner input[name="tabIndex"]').val();
    tabIndex = Number(tabIndex);
    if(tabIndex!=0){
    	$('#search-hd .pholder').eq(tabIndex).show().siblings('.pholder').hide(0);
        $('#search-hd .search-input').eq(tabIndex).show().siblings('.search-input').hide(0);
        $('#globalSearchForm li').eq(tabIndex).addClass('selected').siblings().removeClass('selected');
    }
});

//页面顶部全局搜索
$('#globalSearchForm').submit(function(e){
	var ary = ['indent', 'enterprise', 'sample'];
	var $form = $(e.target);
	var $inputs = $form.find('input');
	//判断当前要搜索的是订单、工厂、样品中的哪一个
	for(var i=0; i<$inputs.length; i++){
		var $input = $($inputs[i]);
		if($input.css('display') != 'none'){
			$input.val($input.val().trim());//清除空白符
			var action = ary[i]+'/search';
			$form.attr('action',action);
		}
	}
});