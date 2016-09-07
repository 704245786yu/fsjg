var g_state = {0:'未收到报价',1:'已收到报价',2:'已接单',3:'已失效'};//订单状态
var g_total = null;

$(function(){
	$('input[name="daterange"]').daterangepicker({
		startDate:moment().subtract(1,'month'),
		locale:{
			format:'YYYY-MM-DD',
			applyLabel: '确定',
			cancelLabel: '取消',
			daysOfWeek:['日', '一', '二', '三', '四', '五',	'六'],
			monthNames:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		}
	});
	$('input[name="indentNum"').mask('#');
	
	var options = $('#dg').bootstrapTable('getOptions');
	options.url = "indent/myReceivedQuote";
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
	var daterange = $('input[name="daterange"]').val();
	params.indentNum = indentNum;
	params.indentName = indentName;
	var dates = daterange.split(' - ');
	params.beginDate = dates[0];
	params.endDate = dates[1];
	
	//判断是否传递total值
	if(g_total != null)
		params.total = g_total;
	delete params.order;
	return params;
}

//审核状态
function stateFormatter(value,row,index){
	return g_state[value];
}

//搜索
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}

function operFormatter(value,row,index){
	var btn = "<button type='button' class='btn' onclick='showDialog("+row.indentNum+")'>确认订单</button>";
	return btn;
}

//显示确认订单模态框
function showDialog(indentNum){
	$.get('indentQuote/getQuoteEnterprise/'+indentNum,function(data){
		$('#dg2').bootstrapTable('load', data);
		$('#formModal').modal('show');
	});
}
