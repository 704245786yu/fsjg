var g_type = {1:'menu',2:'控制器',3:'方法'};//资源类型
$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

//资源类型
function typeFormatter(value,row,index){
	return g_type[value];
}

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

//新增
function add(){
	$('#ff').attr('action','auth/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'auth/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'auth/delete/');
}