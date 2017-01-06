var g_costumeCategoryTop = {'1000':'针织','2000':'梭织','6000':'皮革皮草','3000':'服饰','4000':'服饰辅料','7000':'鞋类','5000':'家纺'};
var g_processType = {'1':'清加工','2':'经销单','3':'其他'};

$(function(){
	initTable('#personIndent');
	initTable('#enterpriseIndent');
	initAd();
	initNewstQuoteList();
});

function initTable(tableId){
	var $trs = $(tableId).find('tr');
	for(var i=1; i<$trs.length; i++){
		var $tr = $($trs[i]);
		//产品类别
		var $costumeCodes = $tr.find('div[name="costumeCodes"]');
		var costumeCode = $costumeCodes.html().split(',')[0];
		var costumeName = g_costumeCategoryTop[costumeCode];
		if(costumeName == null || costumeName == undefined)
			costumeName = getNameByCodes(costumeCode);
		$costumeCodes.html(costumeName);
		//是否急单
		var isUrgency = $tr.find(':hidden[name="isUrgency"]').val();
		if(isUrgency)
			$tr.find('span[name="isUrgency"]').css('visibility','visible');
		//订单类型(加工类型)
		var $processType = $tr.find('td[name="processType"]');
		var processType = g_processType[$processType.html()];
		$processType.html(processType);
	}
}

//最新报价订单
function initNewstQuoteList(){
	var $processType = $('#newstQuoteList table span[name="processType"]');
	for(var i=0;i<$processType.length;i++){
		var processType = $processType.html();
		$processType.html(g_processType[processType]);
	}
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
	var $aAds = $('.ad');
	for(var i=0;i<adPositions.length;i++){
		var $a = $($aAds[i]);
		setAdhrefAndImg($a,adPositions[i]);
	}
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

//热门区域
$('.hotAreaDiv table:first-child a').click(function(e){
	e.preventDefault();
	var href = $(this).attr('href');
	var title = $(this).attr('title');
	if(title!="province")
		title = "city";
	location.href="indent/search?"+title+"="+href;
});
$('.hotAreaDiv table:first-child + table a').click(function(e){
	e.preventDefault();
	var href = $(this).attr('href');
	location.href="indent/search?province="+href;
});