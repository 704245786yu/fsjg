var g_processType = new Object();
var g_auditState = {0:'待审核',1:'未通过',2:'已通过'};

$(function(){
	//初始化g_processType，供table的processType格式化显示用
	var processTypes = $('#processType option');
	$.each(processTypes, function(i,n){
		g_processType[n.value] = n.text; 
	});
	$('input[name="daterange"]').daterangepicker({
//		maxDate: new Date(),
		timePicker: true,
		timePicker24Hour:true,
        timePickerIncrement: 1,
		locale:{
			format:'YYYY-MM-DD HH:mm',
			applyLabel: '确定',
			cancelLabel: '取消'
		}
	});
	//启用Tooltips工具提示
	$("[data-toggle='tooltip']").tooltip();
});

function getQueryParams(params){
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
	delete params.order;
	return params;
}

//审核状态
function auditStateFormatter(value,row,index){
	return g_auditState[value];
}

//日期格式化
function dateFormatter(value,row,index){
	return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}

//根据常量名称搜索
function search(){
	$('#dg').bootstrapTable('selectPage',1);
}

//查询框回车执行查询操作
$('#searchText').keydown(function(event){
	if(event.keyCode == 13){
		search();
	}
});