var g_costumeCategory = null;
var g_processType = null;
var g_district = null;

$(function(){
	initCostumeCategoryObj();
	initProcessType();
	initDistrict();
	initEnterpriseList()
//	getCostumeCategory();
});

function initCostumeCategoryObj(){
	var str = $('#costumeCategoryMap').html();
	str = str.replace(/{/,'{"');
	str = str.replace(/=/g,'":"');
	str = str.replace(/, /g,'\","');
	str = str.replace(/}/g,'"}');
	g_costumeCategory = $.parseJSON(str);
	var $costumeCategory = $('#costumeCategory');
	var index=0;
	$.each(g_costumeCategory,function(i,n){
		if(index<21)
			$('<a>').html(n).attr('href',i).appendTo($costumeCategory);
		else
			$('<a class="excessA">').html(n).attr('href',i).css('display','none').appendTo($costumeCategory);
		index++;
	});
}

//加工类型
function initProcessType(){
	var $aAry = $('#processType a');
	g_processType = new Object();
	for(var i=1; i<$aAry.length; i++){
		var $a = $($aAry[i]);
		var typeValue = $a.attr('href');
		var typeName = $a.html();
		g_processType[typeValue] = typeName;
	}
}

//地区信息
function initDistrict(){
	var str = $('#districts').html();
	str = str.replace(/,}/,'}');
	g_district = $.parseJSON(str);
}

/*function getCostumeCategory(){
	$.get('costumeCategory/getAll',function(data){
		var $costumeCategory = $('#costumeCategory');
		for(var i=1; i<=16; i++){	//下标0是服饰类型的根
			$('<a>').html(data[i].categoryName).attr('href',data[i].categoryCode).appendTo($costumeCategory);
		}
		for(var i=17; i<data.length; i++){
			$('<a class="excessA">').html(data[i].categoryName).attr('href',data[i].categoryCode).css('display','none').appendTo($costumeCategory);
		}
	});
}*/

function initEnterpriseList(){
	var $tables = $('#enterpriseListDiv table');
	for(var i=0; i<$tables.length; i++){
		var $table = $($tables[i]);
		//加工类型
		var $span = $table.find('span[name="processType"]');
		var processType = $.parseJSON('['+$span.html()+']');
		var str = "";
		for(var j=0; j<processType.length; j++){
			str += g_processType[processType[j]]+' ';
		}
		$span.html(str);
		//所在地区
		$span = $table.find('span[name="disctrict"]');
		var disctricts = $.parseJSON($span.html());
		str = "";
		for(var j=0; j<disctricts.length; j++){
			str += g_district[disctricts[j]]+' ';
		}
		$span.html(str);
		//主营产品
		$span = $table.find('span[name="costumeName"]');
		var costumeCategoryAry = $.parseJSON($span.html());
		var costumeStr = "";
		for(var j=0; j<costumeCategoryAry.length; j++){
			costumeStr += g_costumeCategory[costumeCategoryAry[j]]+' ';
		}
		$span.html(costumeStr);
	}
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
