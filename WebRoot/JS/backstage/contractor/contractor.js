var g_auditState = {0:'待审核',1:'未通过',2:'已通过'};
var g_processType = {1:'清加工',2:'经销',3:'来料加工',4:'自营出口',5:'其他'};
var g_userState = {"0":"正常","1":"冻结"};	//用户状态
var g_total = null;

$(function(){
	$('input[name="telephone"').mask('#');
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
});

function queryParams(params){
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
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

//用户状态
function stateFormatter(value,row,index){
	return g_userState[value];
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
	new BsFormTableExtend().delRecord(index,id,'person/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		if(data.result == 1){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
//		  		var pageSize = opt.pageSize;
			var pageNumber = opt.pageNumber;
			console.log(pageNumber);
//		  		var totalRows = opt.totalRows;
//		  		var tzPageNumber = Math.ceil(totalRows/pageSize);
			$('#dg').bootstrapTable('selectPage',pageNumber);
		}else{
			alert('上传文件失败');
		}
	}
});
