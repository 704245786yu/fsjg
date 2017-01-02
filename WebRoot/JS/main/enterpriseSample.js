var g_costumeCode = null;

$(function(){
	initCostumeCate();
	initPagination();//初始化分页
});

//产品类别树
function initCostumeCate(){
	var costumeCateMap = $.parseJSON($('#costumeCateMap').text());
	//产品类别列表
	var $costumeCateUl = $('.list-group');
	var $costumeCateLi = $('#costumeCateLi .list-group-item'); 
	//产生二级类目
	$.each(costumeCateMap,function(key,value){
		if(key<10000){
			var $tempList = $costumeCateLi.clone();
			//设置列表的id，方便下面生成三级类目
			$tempList.attr('id','code'+key);
			$tempList.children('a[name="secCate"]').attr('href',key).text(value);
			$costumeCateUl.append($tempList);
		}
	});
	//产生三级类目
	$.each(costumeCateMap,function(key,value){
		if(key>10000){
			var pCode = (key / 100).toFixed();
			var $costumeCateLi = $('#code'+pCode);
			var $div = $costumeCateLi.children('div')
			$div.append('<div><a href="'+key+'" onclick="return querySample(this)">'+value+'</a></div>');
		}
	});
	
	//产品类别+/-号点击样式切换，以及二级类目的显示与隐藏
	$('.faSquare').click(function(){
		$(this).children('i').toggleClass('fa-minus-square-o');
		$(this).children('i').toggleClass('fa-plus-square-o');
		$(this).nextAll('div').toggle();
		return false;
	});
}

function querySample(a){
	g_costumeCode = $(a).attr('href');
	query();
	return false;
}

function query(offset,totalRows){
	//没有offset表示非翻页操作，需重新初始化分页控件
	if(offset == undefined)
		offset = 0;
	var enterpriseNum = $('#enterpriseNum').val();
	$.get('costumeSample/getEntSample',{'enterpriseNum':enterpriseNum,'costumeCode':g_costumeCode,'offset':offset,'total':totalRows},function(data){
		//isResetPagination为true需重新初始化分页控件
		if(totalRows==undefined){
			resetPagination(data.total);
		}
		generateList(data);
	});
}

function generateList(data){
	var $list = $('#list');
	$list.empty();
	var $template = $('#template div');
	var rows = data.rows;
	for(var i=0;i<rows.length;i++){
		var sample = rows[i];
		console.log(sample);
//		var $temp = $template.clone();
//		var $a = $temp.children('a');
//		$a.attr('href',');
//		$a.children('img').attr('src','u);
//		$a.children('p').text();
		$list.append('<div class="col-md-3 col-xs-3">'+'<a target="_blank" href="costumeSample/showDetail/'+sample.num+'">'+'<img src="uploadFile/costumeSample/'+sample.img+'" style="width:100%;height:200px;">'+'<p>'+sample.name+'</p></a></div>');
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
	});
}

function resetPagination(totalRows){
	var totalPages = Math.ceil(totalRows/20);
	$("#bsPagination").bs_pagination({
		currentPage: 1,
		'totalPages':totalPages,
		'totalRows': totalRows
	});
}