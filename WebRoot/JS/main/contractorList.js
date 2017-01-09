var g_tradeAndCostumeMap = null;
var g_costumeCategory = null;

$(function(){
	initTradeAndCostumeObj();
	initAd();
	setCostumeCategoryDiv(0);
	initIndentList();
	initPagination();//初始化分页
});

function initTradeAndCostumeObj(){
	var str = $('#tradeAndCostumeMap').html();
	g_tradeAndCostumeMap = $.parseJSON(str);
	g_costumeCategory = {};
	for(var i=0; i<g_tradeAndCostumeMap.length; i++){
		$.extend(g_costumeCategory,g_tradeAndCostumeMap[i].children);
	}
	setCostumeCategoryDiv(0);
}

//设置链接地址和图片，供initAd()方法调用
function setAdhrefAndImg($a,ad){
	if(ad.linkType==0)//外部链接
		$a.attr('href','http://'+ad.link);
	else if(ad.linkType==1)//工厂详情页
		$a.attr('href','enterprise/showDetail/'+ad.link);
	
	$a.children('img').attr('src','uploadFile/ad/'+ad.img);
}

function initAd(){
	var adPositions = $.parseJSON($('#adPositions').html());
	var $aAds = $('a.ad');
	for(var i=0;i<adPositions.length;i++){
		var $a = $($aAds[i]);
		setAdhrefAndImg($a,adPositions[i]);
	}
	
}

//行业分类a点击事件
$('#tradeDiv a').click(function(e){
	e.preventDefault();
	var href = $(this).attr('href');
	setCostumeCategoryDiv(href-1);
});

//排序a点击事件
/*$('#tableSort a').click(function(e){
	e.preventDefault();
	$('#tableSort a').css('color','black');
	var $a = $(this);
	$a.css('color','#4EB1E5');
	var href = $a.attr('href');
	$('input[name="sortMark"]').val(href);
	query();
});*/

function setCostumeCategoryDiv(index){
	//默认显示第一个行业分类下的服饰类型
	var costumeCategory = g_tradeAndCostumeMap[index].children;
	var $costumeCategory = $('#costumeCategory');
	$costumeCategory.children('a:first ~ a').remove();
	var index=0;
	$.each(costumeCategory,function(i,n){
		if(index<30)
			$('<a onclick="return aClick(this)">').html(n).attr('href',i).appendTo($costumeCategory);
		else
			$('<a class="excessA" onclick="return aClick(this)">').html(n).attr('href',i).css('display','none').appendTo($costumeCategory);
		index++;
	});
	hiddenMoreAlabel();
}

function aClick(a){
	var $a = $(a)
	$a.parent().find('a.label').removeClass('label label-info');
	$a.addClass('label label-info');
	query();
	return false;
}

//确定选择地区
function checkDistrict(){
	var $select = $('#districtContainer :selected');
	var str = '';
	for(var i=0; i<$select.length; i++){
		if($($select[i]).val() != '')
			str += $($select[i]).text()+' ';
	}
	if(str == '')
		str = '选择发单地区';
	$('#districtBtn').html(str);
	query();
}

//订单查询
function query(offset,totalRows){
	//没有offset表示非翻页操作，需重新初始化分页控件
	if(offset == undefined)
		offset = 0;
	var costumeCode = $('#costumeCategory a.label').attr('href');
	costumeCode = costumeCode == 0 ? null : costumeCode;
	var $district = $('#districtContainer');
	var province = $district.find('#province').val();
	var city = $district.find('#city').val();
	var county = $district.find('#county').val();
	var town = $district.find('#town').val();
	
//	var processType = $('#processType a.label').attr('href');
//	processType = processType == 0 ? null : processType;
	var processYear = $('#processYear a.label').attr('href');
	processYear = processYear == 0 ? null : processYear;
	//排序
//	var sortMark = $('input[name="sortMark"]').val();
	$.get('contractor/search',{'costumeCode':costumeCode, 'province':province, 'city':city, 'county':county, 'town':town,
		'processYear':processYear,'offset':offset,'total':totalRows},function(data){
		//isResetPagination为true需重新初始化分页控件
		if(totalRows==undefined){
			resetPagination(data.total);
		}
		generateTable(data);
	});
}

function initIndentList(){
	$.get('contractor/search',{offset:0},function(data){
		resetPagination(data.total);
		generateTable(data);
	});
}

//行点击事件
function trClick(id){
	window.open("contractor/showDetail/"+id);
}

function generateTable(data){
	var $tbody = $('#contractorTable tbody');
	$tbody.empty();
	var rows = data.rows;
	for(var i=0;i<rows.length;i++){
		var contractor = rows[i];
		var $tr = $('<tr onclick="trClick('+contractor.id+')">');
		if(contractor.gender=='男')
			$tr.append($('<td>').html('<img src="image/man.png">'));
		else
			$tr.append($('<td>').html('<img src="image/woman.png">'));
		$tr.append($('<td>').html(contractor.age));
		$tr.append($('<td>').html(contractor.processYear));
		$tr.append($('<td>').html(contractor.workAmount));
		$tr.append($('<td>').html(contractor.district));
		
		//专业技能
		var costumeCategoryAry = contractor.costumeCode.split(',');
		var costumeStr = "";
		for(var j=0; j<costumeCategoryAry.length; j++){
			costumeStr += g_costumeCategory[costumeCategoryAry[j]]+' ';
		}
		
		$tr.append($('<td>').html(costumeStr));
		//工作场地
		var workSpace = null;
		if(contractor.workSpace==0)
			workSpace = '在家';
		else if(contractor.workSpace==1)
			workSpace = '到厂';
		$tr.append($('<td>').html(workSpace));
		
		$tbody.append($tr);
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

/**隐藏产品类别更多标签*/
function hiddenMoreAlabel(){
	var $a = $('#showMoreToggle');
	var $p = $a.prev();
	var height = $p.height();
	var lineHeight = parseInt($p.css('line-height'));
	//展示
	if($p.css('overflow') == 'hidden'){
	}//隐藏
	else{
		$p.css('overflow','hidden');
		$p.height(height-lineHeight);
		$('a.excessA').css('display','none');
		$a.children('span').attr('class','glyphicon glyphicon glyphicon-chevron-down');
	}
}

function initPagination(){
	var totalRows = $('#totalRows').val();
	var totalPages = Math.ceil(totalRows/20);
	$("#bsPagination").bs_pagination({
		currentPage: 1,
		rowsPerPage: 20,
		'totalPages':totalPages,
		'totalRows': totalRows,
		visiblePageLinks: 8,//显示页面链接数
		showGoToPage: false,//显示页面跳转控件
		showRowsPerPage: false,//显示每页行数
		showRowsInfo: false,	//当前页面的行数信息
		showRowsDefaultInfo: false,
		//样式
		containerClass:"",
		navListContainerClass: "col-xs-12 col-sm-12 col-md-12",
		onChangePage: function(event, data){
			var totalRows = $("#bsPagination").bs_pagination('getOption', 'totalRows');
			var offset = (data.currentPage - 1)*data.rowsPerPage;
			query(offset,totalRows);
		}
	})
}

function resetPagination(totalRows){
	var totalPages = Math.ceil(totalRows/20);
	$("#bsPagination").bs_pagination({
		currentPage: 1,
		'totalPages':totalPages,
		'totalRows': totalRows
	});
}