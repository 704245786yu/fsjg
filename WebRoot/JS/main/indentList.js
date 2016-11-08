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

//排序a点击事件
$('#tableSort a').click(function(e){
	e.preventDefault();
	$('#tableSort a').css('color','black');
	var $a = $(this);
	$a.css('color','#4EB1E5');
	var href = $a.attr('href');
	$('input[name="sortMark"]').val(href);
	query();
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
	var processType = $('#processType a.label').attr('href');
	processType = processType == 0 ? null : processType;
	var saleMarket = $('#saleMarket a.label').attr('href');
	saleMarket = saleMarket == 0 ? null : saleMarket;
	//发单用户
	var userType = $('#userType a.label').attr('href');
	userType = userType == 0 ? null : userType;
	//排序
	var sortMark = $('input[name="sortMark"]').val();
	//只看急单
	var isUrgency = $('#tableSort input[type="checkbox"]').prop('checked');
	var $div = $('#indentDiv');
	$div.empty();
	//顶部搜索框
	var $keyword = $('input[name="indentKeyword"]');
	var indentKeyword = $keyword.val().trim();
	$keyword.val(indentKeyword);//清除空白符
	$.get('indent/search2',
		{'costumeCode':costumeCode, 'province':province, 'city':city, 'county':county, 'town':town,
		'processType':processType, 'saleMarket':saleMarket,'indentKeyword':indentKeyword,'sortMark':sortMark,'userType':userType,'isUrgency':isUrgency,'offset':offset,'total':totalRows},
		function(data){
			//isResetPagination为true需重新初始化分页控件
			if(totalRows==undefined){
				resetPagination(data.total);
			}
			var rows = data.rows;
			var $template = $('.template');
			for(var i=0; i<rows.length; i++){
				var indent = rows[i];
				var $table = $template.clone();
				//是否急单
				if(indent.isUrgency)
					$table.find('img[name="urgencyImg"]').css('display','');
				//订单名称
				var $titleA = $table.find('a[name="title"]');
				$titleA.attr('href',$titleA.attr('href')+indent.indentNum).html(indent.indentName);
				//订单数量
				var $quantityDiv = $table.find('div[name="quantity"]');
				$quantityDiv.html('预计订单数量：'+indent.quantity+'件');
				//预计交货日期
				var $preDeliveryDateDiv = $quantityDiv.next();
				$preDeliveryDateDiv.html('预计交货日期：'+moment(indent.preDeliveryDate).format('YYYY-MM-DD'));
				//销售市场
				var $saleMarketDiv = $preDeliveryDateDiv.next();
				var saleMarket = indent.saleMarket;
				if(saleMarket == 1){
					$saleMarketDiv.html('销售市场：内销');
				}else if(saleMarket == 2){
					$saleMarketDiv.html('销售市场：外销');
				}
				//加工类型
				var $processTypeDiv = $table.find('div[name="processType"]');
				var processType = comm_getProcessTypeName(indent.processType);
				$processTypeDiv.html(processType);
				//订单类型
				var $indentType = $processTypeDiv.next();
				//订单类型
				if(indent.indentType == 0){
					$indentType.html('来图/来样加工');
				}else if(indent.indentType == 1){
					$indentType.html('看款下单');
				}
				//接单地区
				var $condDistrict = $table.find('div[name="condDistrict"]');
				var provinceAndCity = '';//默认情况
				if(indent.condProvince != null)
					provinceAndCity += comm_getDistrictName(indent.condProvince,' ');
				if(indent.condCity != null)
					provinceAndCity += comm_getDistrictName(indent.condCity,' ');
				$condDistrict.html(provinceAndCity);
				//接单要求
				var $condDemandDiv = $condDistrict.next();
				$condDemandDiv.html(indent.condDemand);
				//发单人及所在地区
				var userType = indent.userType;
				var userTypeStr = '';
				if(userType == 1){
					userTypeStr = '某个体户';
				}else{
					userTypeStr = '某工厂';
				}
				var $district = $table.find('div[name="district"]');
				var district = '';//默认情况
				if(indent.province != null && indent.city != null)
					district = comm_getDistrictName(indent.province+','+indent.city,',');
				$district.html(district+userTypeStr);
				//发单时间
				$table.find('div[name="createTime"]').html(moment(indent.createTime).format('YYYY-MM-DD'));
				//有效日期
				$table.find('div[name="effectiveDate"]').html(moment(indent.effectiveDate).format('YYYY-MM-DD'));
				$div.append($table);
			}
		}
	);
}

function initIndentList(){
	var $tables = $('#indentDiv table');
	for(var i=0; i<$tables.length; i++){
		var $tds = $($tables[i]).find('td');
		
		//1、销售市场
		var span = $($tds[1]).find('span');
		var saleMarket = parseInt(span.html());
		if(saleMarket == 1){
			span.html('内销');
		}else if(saleMarket == 2){
			span.html('外销');
		}
		
		//2、订单类型
		var $divs = $($tds[2]).find('div');
		//加工类型
		var processType = $divs[0].innerHTML;
		$divs[0].innerHTML = comm_getProcessTypeName(processType);
		//订单类型
		if($divs[1].innerHTML == 0){
			$divs[1].innerHTML = '来图/来样加工';
		}else if($divs[1].innerHTML == 1){
			$divs[1].innerHTML = '看款下单';
		}
		
		//3、接单企业要求所在地区
		var div = $($tds[3]).find('div')[0];
		var str = comm_getDistrictName(div.innerHTML,' ');
		div.innerHTML = str=='' ? '所有地区':str;
		
		//4、发单企业
		var $children = $($tds[4]).children();
		disctricts = $children[1].innerHTML.split(',');
		province = g_district[disctricts[0]];
		var districtStr = '';
		if(province == undefined){
			districtStr = '';			
		}else{
			city = g_district[disctricts[1]];
			if(city == undefined)
				districtStr = province;
			else
				districtStr = province+city;
		}
		var userType = $children[0].value;
		if(userType == 1){
			$children[1].innerHTML = districtStr+'某个体户';
		}else{
			$children[1].innerHTML = districtStr+'某工厂';
		}
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