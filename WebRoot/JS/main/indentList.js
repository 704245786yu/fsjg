var g_costumeCategory = null;
var g_processType = null;
var g_district = null;

$(function(){
	initCostumeCategoryObj();
	initProcessType();
	initDistrict();
	initIndentList();
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
			$('<a onclick="return aClick(this)">').html(n).attr('href',i).appendTo($costumeCategory);
		else
			$('<a class="excessA" onclick="return aClick(this)">').html(n).attr('href',i).css('display','none').appendTo($costumeCategory);
		index++;
	});
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
function query(){
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
	$.get('enterprise/search3',
		{'costumeCode':costumeCode, 'province':province, 'city':city, 'county':county, 'town':town, 'processType':processType, 'staffNumber':staffNumber},
		function(data){
			var rows = data.rows;
			var $template = $('.template').clone().css('display','table');
			for(var i=0; i<rows.length; i++){
				var enterprise = rows[i];
				var $table = $template.clone();
				var $titleA = $table.find('.title a');
				$titleA.attr('href',$titleA.attr('href')+enterprise.id).html(enterprise.enterpriseName);
				//加工类型
				var $span = $table.find('span[name="processType"]');
				var processType = $.parseJSON('['+enterprise.processType+']');
				var str = "";
				for(var j=0; j<processType.length; j++){
					str += g_processType[processType[j]]+' ';
				}
				$span.html(str);
				//员工人数
				$table.find('.staffNumber').html('员工人数：'+enterprise.staffNumber);
				//工厂介绍
				$table.find('span[name="description"]').html(enterprise.description);
				//所在地区
				$span = $table.find('span[name="disctrict"]');
				var disctricts = $.parseJSON('['+enterprise.province+','+enterprise.city+']');
				str = "";
				for(var j=0; j<disctricts.length; j++){
					str += g_district[disctricts[j]]+' ';
				}
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

function initIndentList(){
	var $tables = $('#indentDiv table');
	for(var i=0; i<$tables.length; i++){
		var $tds = $($tables[i]).find('td');
		
		//1、销售市场
		var span = $($tds[1]).find('span');
		var saleMarket = span.html();
		if(saleMarket == 1){
			span.html('内销');
		}else if(saleMarket == 2){
			span.html('外销');
		}
		
		//2、订单类型
		var $divs = $($tds[2]).find('div');
		//加工类型
		var processType = $divs[0].innerHTML;
		$divs[0].innerHTML = g_processType[processType];
		//订单类型
		if($divs[1].innerHTML == 1){
			$divs[1].innerHTML = '来图/来样加工';
		}else if($divs[1].innerHTML == 2){
			$divs[1].innerHTML = '看款下单';
		}
		
		//3、接单企业要求所在地区
		var div = $($tds[3]).find('div')[0];
		var disctricts = div.innerHTML.split(',');
		var province = g_district[disctricts[0]];
		if(province == undefined){
			div.innerHTML = '所有地区';			
		}else{
			var city = g_district[disctricts[1]];
			if(city == undefined)
				div.innerHTML = province;
			else
				div.innerHTML = province+' '+city;
		}
		
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
