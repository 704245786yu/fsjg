$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

var g_data = null;//保存datagrid加载的数据，用于之后的search()查询用
$('#dg').bootstrapTable({
	onLoadSuccess: function (data) {
		g_data = data;
    }
});

//根据常量名称搜索
function search(){
	var param = $('#constantTypeNameParam').val().trim();
	//空串重载所有数据
	if(param == ''){
		$('#dg').bootstrapTable('load',g_data);
	}else{
		//过滤数据
		var newData = new Array();
		for(var i=0; i<g_data.length; i++){
			var constantTypeName = g_data[i].constantTypeName;
			if(constantTypeName.indexOf(param,0) != -1)
				newData.push(g_data[i]);
		}
		$('#dg').bootstrapTable('load',newData);
	}
}

//查询框回车执行查询操作
$('#constantTypeNameParam').keydown(function(event){
	if(event.keyCode == 13){
		search();
	}
});

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	constantTypeName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 10,
    				message: '最多10个字符'
    			}
    		}
    	},
    	constantTypeCode: {
            message: '常量类型编码无效',
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '请输入1到20个字符'
                },
                regexp: {
                    regexp: /^[a-zA-Z_]+$/,
                    message: '只能由字母和下划线组成'
                }
            }
        }
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

//新增
function add(){
	$('#ff').attr('action','constantType/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'constantType/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'constantType/delete/');
}