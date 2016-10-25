var g_tradeAndCostumeMap = null;
var g_costumeCategory = null;

$(function(){
	initTradeAndCostumeObj();
	setCostumeCategoryDiv(0);
	initAd();
	initEnterpriseList();
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

//设置链接地址和图片，供initAdd()方法调用
function setAdhrefAndImg($a,ad){
	if(ad.linkType==0)//外部链接
		$a.attr('href','http://'+ad.link);
	else if(ad.linkType==1)//工厂详情页
		$a.attr('href','enterprise/showDetail/'+ad.link);
	
	$a.children('img').attr('src','uploadFile/ad/'+ad.img);
}

function initAd(){
	var adPositions = $.parseJSON($('#adPositions').html());
	var $a = $('.ad');
	setAdhrefAndImg($a,adPositions.shift());
	
	//按年龄从小到大排序
	adPositions.sort(function(a,b){
		return a.sort-b.sort
	});
	
	var $ul = $('#adUl');
	var $li = $('div[name="sample"] li');
	for(var i=0;i<adPositions.length;i++){
		var $tempLi = $li.clone();
		$a = $tempLi.children('a');
		setAdhrefAndImg($a,adPositions[i]);
		var $div = $tempLi.children('div');
		$div.html(adPositions[i].title);
		$ul.append($tempLi);
	}
}

//行业分类a点击事件
$('#tradeDiv a').click(function(e){
	e.preventDefault();
	var href = $(this).attr('href');
	setCostumeCategoryDiv(href-1);
});

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

//工厂查询
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
	var processType = $('#processType a.label').attr('href');
	processType = processType == 0 ? null : processType;
	var staffNumber = $('#staffNumber a.label').attr('href');
	staffNumber = staffNumber == 0 ? null : staffNumber;
	var $div = $('#enterpriseListDiv');
	$div.empty();
	//顶部搜索框
	var $keyword = $('input[name="enterpriseKeyword"]');
	var enterpriseKeyword = $keyword.val().trim();
	$keyword.val(enterpriseKeyword);//清除空白符
	$.get('enterprise/search2',
		{'costumeCode':costumeCode, 'province':province, 'city':city, 'county':county,
		'town':town,'processType':processType, 'staffNumber':staffNumber, 
		'enterpriseKeyword':enterpriseKeyword,'offset':offset,'total':totalRows},
		function(data){
			//isResetPagination为true需重新初始化分页控件
			if(totalRows==undefined){
				resetPagination(data.total);
			}
			var rows = data.rows;
			var $template = $('.template').clone().css('display','table');
			for(var i=0; i<rows.length; i++){
				var enterprise = rows[i];
				var $table = $template.clone();
				//logo
				$table.find('img').attr('src','uploadFile/enterprise/'+enterprise.logo);
				var $titleA = $table.find('.title a');
				$titleA.attr('href',$titleA.attr('href')+enterprise.id).html(enterprise.enterpriseName);
				//加工类型
				var $span = $table.find('span[name="processType"]');
				var str = comm_getProcessTypeName(enterprise.processType);
				$span.html(str);
				//员工人数
				$table.find('.staffNumber').html('员工人数：'+enterprise.staffNumber);
				//工厂介绍
				var $description = $table.find('span[name="description"]');
				$description.html(enterprise.description);
				//更多详情
				var $amore = $description.next('a');
				$amore.attr('href','enterprise/showDetail/'+enterprise.id);
				//所在地区
				$span = $table.find('span[name="disctrict"]');
				str = g_district[enterprise.province]+' '+g_district[enterprise.city];
				$span.html(str);
				//主营产品
				$span = $table.find('span[name="costumeName"]');
				var costumeCategoryAry = enterprise.costumeCode;
				var costumeStr = "";
				for(var j=0; j<costumeCategoryAry.length; j++){
					costumeStr += g_costumeCategory[costumeCategoryAry[j]]+' ';
				}
				$span.html(costumeStr);
				$div.append($table);
			}
		}
	);
}

function initEnterpriseList(){
	var $tables = $('#enterpriseListDiv table');
	for(var i=0; i<$tables.length; i++){
		var $table = $($tables[i]);
		//加工类型
		var $span = $table.find('span[name="processType"]');
		var str = comm_getProcessTypeName($span.html());
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