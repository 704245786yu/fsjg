var g_costumeCategory = null;	//服饰类型

$(function(){
	init();
	getExcellent();
	getNewest();
	getNewAuth();
});

function init(){
	str = $('#hiddenCostumeCategory').html();
	g_costumeCategory = $.parseJSON(str);
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
}

$('form').submit(function(){
	$('form :checked').prop('disabled',false);
});

//获取优秀工厂(接单工厂)
function getExcellent(){
	var $enterpriseList = $('#enterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getExcellent',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			//logo
			$new.find('img.media-object').attr('src','uploadFile/enterprise/'+enterprise.logo);
			var $head = $new.find('.media-heading');
			$head.children('.media-heading > a').text(enterprise.enterpriseName).attr('href','enterprise/showDetail/'+enterprise.id);
			var $list1 = $head.next().text('员工人数：'+enterprise.staffNumber+'人');
			//加工类型
			var processTypeAry = enterprise.processType.split(',');
			var processType = '';
			for(var j=0; j<processTypeAry.length; j++){
				processType += g_processType[processTypeAry[j]]+' ';
			}
			var $list2 = $list1.next().text('加工类型：'+processType);
			//主营产品
			var costumeCodes = enterprise.costumeCode;
			var costumeStr = '';
			for(j=0; j<costumeCodes.length; j++){
				costumeStr += g_costumeCategory[costumeCodes[j]]+' ';
			}
			$list2.next().text('主营产品：'+costumeStr);
			//档期
			$enterprise.find('div[name="schedule"]').append(enterprise.schedule);
			$enterpriseList.append($new);
		}
	});
}

//最新入住的企业
function getNewest(){
	var $enterpriseList = $('#newEnterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getNewest',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading').text(enterprise.enterpriseName);
			var costumeCodes = enterprise.costumeCode;
			var costumeStr = '';
			for(j=0; j<costumeCodes.length; j++){
				costumeStr += g_costumeCategory[costumeCodes[j]]+' ';
			}
			$head.next().text('主营产品：'+costumeStr);
			$enterpriseList.append($new);
		}
	});
}

//最新认证加工厂
function getNewAuth(){
	var $enterpriseList = $('#newAuthEnterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getNewAuth',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading').text(enterprise.enterpriseName);
			var costumeCodes = enterprise.costumeCode;
			var costumeStr = '';
			for(j=0; j<costumeCodes.length; j++){
				costumeStr += g_costumeCategory[costumeCodes[j]]+' ';
			}
			$head.next().text('主营产品：'+costumeStr);
			$enterpriseList.append($new);
		}
	});
}

//热门区域
$('.hotAreaDiv table:first-child a').click(function(e){
	e.preventDefault();
	var href = $(this).attr('href');
	var name = null;
	if(href=="province")
		name = $(this).attr('title');
	else{
		href = "city";
		name = $(this).html();
	}
	for(var code in g_district){
		if(g_district[code].indexOf(name) != -1){
			location.href="enterprise/search?"+href+"="+code;
		}
	}
});
$('.hotAreaDiv table:first-child + table a').click(function(e){
	e.preventDefault();
	var name = $(this).html();
	for(var code in g_district){
		if(g_district[code].indexOf(name) != -1){
			location.href="enterprise/search?province="+code;
		}
	}
});