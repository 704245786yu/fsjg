var g_type = ['','开业','促销','库存','活动','求购','零衣柜','店面出租'];

$(function(){
	initAd();
	initPagination();//初始化分页
});

function query(offset,totalRows){
	//没有offset表示非翻页操作，需重新初始化分页控件
	if(offset == undefined)
		offset = 0;
	var type = $('select[name="type"]').val();
	var $district = $('#districtContainer');
	var province = $district.find('#province').val();
	var city = $district.find('#city').val();
	var county = $district.find('#county').val();
	var town = $district.find('#town').val();
	$.get('activity/getList',{'type':type,'province':province, 'city':city, 'county':county, 'town':town, 'offset':offset,'total':totalRows},function(data){
		//isResetPagination为true需重新初始化分页控件
		if(totalRows==undefined){
			resetPagination(data.total);
		}
		generateList(data);
	});
}

$('select[name="type"]').change(function(){
	query();
});

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

//行点击事件
function trClick(id){
	window.open("activity/showDetail/"+id);
}

function generateList(data){
	var $list = $('#list');
	$list.empty();
	var rows = data.rows;
	var $tr = $('<thead style="font-size:16px;">');
	$tr.append($('<th>').text("发布地址"));
	$tr.append($('<th style="text-align:center;">').text("活动类型"));
	$tr.append($('<th style="text-align:center;">').text("活动内容"));
	$tr.append($('<th style="text-align:center;">').text("活动日期"));
	$list.append($tr);
	for(var i=0;i<rows.length;i++){
		var activity = rows[i];
		$tr = $('<tr onclick="trClick('+activity.id+')">');
		$tr.append($('<td>').text(activity.detailAddr));
		$tr.append($('<td style="text-align:center;">').text(g_type[activity.type]));
		$tr.append($('<td style="text-align:center;">').text(activity.title));
		$tr.append($('<td style="text-align:center;">').text(activity.duration));
		$list.append($tr);
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