$(function(){
	initPagination();//初始化分页
});

//订单查询
function query(offset,totalRows){
	//没有offset表示非翻页操作，需重新初始化分页控件
	if(offset == undefined)
		offset = 0;
	$.get('tradeNews/getList',{'offset':offset,'total':totalRows},function(data){
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
	var $template = $('#template > div');
	var rows = data.rows;
	for(var i=0;i<rows.length;i++){
		var tradeNews = rows[i];
		var $tempDiv = $template.clone();
		$tempDiv.children('a').attr('href','tradeNews/showDetail/'+tradeNews.id).text(tradeNews.title);
		$tempDiv.children('div').text(moment(tradeNews.updateTime).format('YYYY-MM-DD HH:mm'));
		$list.append($tempDiv);
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