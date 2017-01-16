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
	var enterpriseNum = $('#enterpriseNum').val();
	var beginDate = $('#listPanel input[name="beginDate"]').val();
	var endDate = $('#listPanel input[name="endDate"]').val();
	params.num = num;
	params.name = name;
	params.enterpriseNum = enterpriseNum;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	params.total = g_total;//g_total可能为null
	
	delete params.order;
	return params;
}

//查询
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'costumeSample/delete/');
}