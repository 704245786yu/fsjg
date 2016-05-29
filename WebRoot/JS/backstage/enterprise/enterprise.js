var g_processType = new Object();
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
});

function getQueryParams(params){
	params.pageSize = params.limit;
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
	delete params.limit;
	delete params.order;
	return params;
}

//加工类型
function processFormatter(value,row,index){
	return g_processType[value];
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
