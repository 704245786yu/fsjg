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
	$('input[name="price"').mask('#');
	
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

//搜索
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}

//订单金额
function expectPriceFormatter(value,row,index){
	return value == -1 ? '面谈':value;
}

function operFormatter(value,row,index){
	var btn = "<button type='button' class='btn btn-default' onclick='showDialog("+row.indentNum+",\""+row.indentName+"\")'>确认订单</button>";
	return btn;
}

function radioFormatter(value,row,index){
	var radio = "<input type='radio' name='enterprise' onclick='onCheckRadio("+row.id+","+row.quote+","+row.telephone+")'>";
	return radio;
}

//============确认订单==============
//显示确认订单模态框
function showDialog(indentNum,indentName){
	$(':hidden[name="indentNum"]').val(indentNum);
	$(':hidden[name="indentName"]').val(indentName);
	$('#errorMsg').html('');
	$.get('indentQuote/getQuoteEnterprise/'+indentNum,function(data){
		$('#dg2').bootstrapTable('load', data);
		$('#formModal').modal('show');
	});
}

function onCheckRadio(enterpriseId,quote,telephone){
	$(':hidden[name="enterpriseId"]').val(enterpriseId);
	$(':hidden[name="quote"]').val(quote);
	$(':hidden[name="telephone"]').val(telephone);
}

$('#confirmFrom').submit(function(e){
	e.preventDefault();
	var $form = $(e.target);
	var action = $form.attr('action');
	var param = $form.serializeObject();
	if(param.enterpriseId == null || param.enterpriseId == ''){
		$('#errorMsg').html('请选择接单工厂');
	}else{
		if(param.price == '')
			param.price = param.quote;
		delete param.quote;
		$.post($form.attr('action'), param, function(data) {
			if(data.status==200){
				$('#dg').bootstrapTable('removeByUniqueId',param.indentNum);
				$('.alert').fadeIn();
				setTimeout("timeOut()",3000);
			}
		},'json');
	}
});

function timeOut(){
	$('#formModal').modal('hide');
	$('.alert').hide();
}