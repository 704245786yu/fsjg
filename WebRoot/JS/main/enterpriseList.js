$(function(){
	getCostumeCategory();
});

function getCostumeCategory(){
	$.get('costumeCategory/getAll',function(data){
		var $costumeCategory = $('#costumeCategory');
		for(var i=1; i<=16; i++){	//下标0是服饰类型的根
			$('<a>').html(data[i].categoryName).attr('href',data[i].categoryCode).appendTo($costumeCategory);
		}
		for(var i=17; i<data.length; i++){
			$('<a class="excessA">').html(data[i].categoryName).attr('href',data[i].categoryCode).css('display','none').appendTo($costumeCategory);
		}
	});
}

$('#showMoreToggle').click(function(e){
	var $a = $(e.target);
	var $p = $a.prev();
	var height = $p.height();
	var lineHeight = parseInt($p.css('line-height'));
	//展示
	if($p.css('overflow') == 'hidden'){
		$p.css('overflow','scroll');
		$p.css('overflow-x','hidden');
		$p.height(height+lineHeight);
		$('a.excessA').css('display','inline');
		$a.children('span').attr('class','glyphicon glyphicon-chevron-up');
	}//隐藏
	else{
		$p.css('overflow','hidden');
		$p.height(height-lineHeight);
		$('a.excessA').css('display','none');
		$a.children('span').attr('class','glyphicon glyphicon glyphicon-chevron-down');
	}
});
