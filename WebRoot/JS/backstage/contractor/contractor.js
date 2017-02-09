var g_auditState = {0:'待审核',1:'未通过',2:'已通过'};
var g_processType = {1:'清加工',2:'经销',3:'来料加工',4:'自营出口',5:'其他'};
var g_total = null;

$(function(){
	$('input[name="telephone"').mask('#');
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
});

$('#dg').bootstrapTable({
	onLoadSuccess:function(number,size){
		g_total = $('#dg').bootstrapTable('getOptions').totalRows;
	}
});

function queryParams(params){
	var userName = $('#listPanel input[name="userName"]').val().trim();
	var telephone = $('#listPanel input[name="telephone"]').val().trim();
	var auditState = $('#listPanel select[name="auditState"]').val();
	var beginDate = $('#listPanel input[name="beginDate"]').val();
	var endDate = $('#listPanel input[name="endDate"]').val();
	params.userName = userName;
	params.telephone = telephone;
	params.auditState = auditState;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	params.total = g_total;//g_total可能为null
	
	delete params.order;
	delete params.order;
	return params;
}

function view(id){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	var pAry = $('#viewModal p');
	for(var i=0; i<pAry.length; i++){
		var $p = $(pAry[i]);
		var name = $p.attr("name");
		console.log(name);
		$p.html(row[name]);
	}
	$('#viewModal').modal('show');//显示form模态框
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
	return moment(value).format('YYYY-MM-DD HH:mm');
}

//根据常量名称搜索
function search(){
	g_total = null;
	$('#dg').bootstrapTable('selectPage',1);
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'contractor/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		var status = data.result.status;
		var value = data.result.value;
		if(status == 200){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
			var pageNumber = opt.pageNumber;
			var totalRows = opt.totalRows;
	  		if(totalRows==0)
	  			$('#dg').bootstrapTable('refresh');
	  		else
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