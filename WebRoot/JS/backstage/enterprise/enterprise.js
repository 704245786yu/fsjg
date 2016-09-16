var g_processType = new Object();
var g_auditState = {0:'待审核',1:'未通过',2:'已通过'};
var g_processType = {1:'清加工',2:'经销',3:'来料加工',4:'自营出口',5:'其他'};

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

//加工类型
function processTypeFormatter(value,row,index){
	if(value==null)
		return;
	var str = '';
	var processAry = value.split(',');
	for(var i=0; i<processAry.length; i++){
		str += g_processType[processAry[i]]+' ';
	}
	return str;
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

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'enterprise/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		var status = data.result.status;
		var value = data.result.value;
		if(status == 200){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
			var pageNumber = opt.pageNumber;
//			console.log(pageNumber);
//  		var totalRows = opt.totalRows;
//  		var tzPageNumber = Math.ceil(totalRows/pageSize);
			$('#dg').bootstrapTable('selectPage',pageNumber);
		}else if(status == 500){
			var errorInfo = "";
			for(var i=0; i<value.length; i++){
				errorInfo += value[i]+'<br/>';
			}
			new JqConfirmExtend().showDialog('上传错误',errorInfo);
		}else if(status == 501){
			new JqConfirmExtend().showDialog('上传错误',value);
		}
	}
});
