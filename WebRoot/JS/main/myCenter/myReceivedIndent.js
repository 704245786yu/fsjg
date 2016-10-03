var g_total = null;

$(function(){
	$('input[name="beginDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn',
		defaultDate:moment().subtract(1,'months')
    });
	$('input[name="endDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn',
		defaultDate:moment()
    });
	$('input[name="indentNum"').mask('#');
	
	var options = $('#dg').bootstrapTable('getOptions');
	options.url = "indent/myReceivedIndent";
	$('#dg').bootstrapTable('refreshOptions',options);
	$('#dg').bootstrapTable('refresh');
});

$('#dg').bootstrapTable({
	onPageChange:function(number,size){
		g_total = $('#dg').bootstrapTable('getOptions').totalRows;
	}
});

function queryParams(params){
	var indentNum = $('input[name="indentNum"]').val();
	var indentName = $('input[name="indentName"]').val();
	var beginDate = $('input[name="beginDate"]').val();
	var endDate = $('input[name="endDate"]').val();
	params.indentNum = indentNum;
	params.indentName = indentName;
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

//查询
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}