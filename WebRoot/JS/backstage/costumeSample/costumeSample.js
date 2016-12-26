var g_total = null;

$(function(){
	$('#listPanel .date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
});

$('#dg').bootstrapTable({
	onPageChange:function(number,size){
		g_total = $('#dg').bootstrapTable('getOptions').totalRows;
	}
});

function queryParams(params){
	var num = $('#listPanel input[name="num"]').val().trim();
	var name = $('#listPanel input[name="name"]').val().trim();
	var enterpriseName = $('#listPanel input[name="enterpriseName"]').val().trim();
	var beginDate = $('#listPanel input[name="beginDate"]').val();
	var endDate = $('#listPanel input[name="endDate"]').val();
	params.num = num;
	params.name = name;
	params.enterpriseName = enterpriseName;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	params.total = g_total;//g_total可能为null
	
	delete params.order;
	return params;
}

//日期格式化
//function dateFormatter(value,row,index){
//	return new Date(value).format("yyyy-MM-dd hh:mm:ss");
//}

//查询
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'costumeSample/delete/');
}