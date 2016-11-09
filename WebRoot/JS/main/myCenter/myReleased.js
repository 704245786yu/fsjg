var g_state = {0:'未收到报价',1:'已收到报价',2:'已接单',3:'已失效'};//订单状态
var g_total = null;

$(function(){
//	$('input[name="beginDate"]').datetimepicker({
//		format: 'YYYY-MM-DD',
//		locale: 'zh-cn',
//    });
//	$('input[name="endDate"]').datetimepicker({
//		format: 'YYYY-MM-DD',
//		locale: 'zh-cn',
//		defaultDate:moment()
//    });
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
	});
	$('input[name="indentNum"').mask('#');
	
	var options = $('#dg').bootstrapTable('getOptions');
	options.url = "indent/myReleased";
	$('#dg').bootstrapTable('refreshOptions',options);
	$('#dg').bootstrapTable('refresh');
});

$('#dg').bootstrapTable({
	onPageChange:function(number,size){
		g_total = $('#dg').bootstrapTable('getOptions').totalRows;
		console.log(g_total);
	}
});

function queryParams(params){
	var indentNum = $('input[name="indentNum"]').val();
	var indentName = $('input[name="indentName"]').val();
	var state = $('select[name="state"]').val();
	var beginDate = $('input[name="beginDate"]').val();
	var endDate = $('input[name="endDate"]').val();
	params.indentNum = indentNum;
	params.indentName = indentName;
	params.state = state;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	//判断是否传递total值
	if(g_total != null)
		params.total = g_total;

	delete params.order;
	return params;
}

//订单金额
function expectPriceFormatter(value,row,index){
	return value == -1 ? '面谈':value;
}

//审核状态
function stateFormatter(value,row,index){
	return g_state[value];
}

//根据常量名称搜索
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}