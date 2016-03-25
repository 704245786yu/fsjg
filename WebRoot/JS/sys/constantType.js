//表单验证
$('#ff').bootstrapValidator({
	message: '常量类型编码无效',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
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
        },
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
        }
    }
}).on('success.form.bv', function(e) {
    // Prevent form submission
    e.preventDefault();
    // Get the form instance
    var $form = $(e.target);
    $.post($form.attr('action'), $form.serialize(), function(result) {
    	$('#dg').datagrid('appendRow',result);
    	$('#formModal').modal('hide');
    },'json');
});

//form模态框关闭是form重置
$('#formModal').on('hide.bs.modal', function (e) {
	 var $target = $(e.target);
	 var $form = $target.find("form");
	 $form.bootstrapValidator('resetForm', true);
});

//新增
function add(){
	$('#ff').attr('action','constantType/save');
}

//修改
function modify(index) {
	$('#dg').datagrid('selectRow',index);
	var row = $('#dg').datagrid('getSelected');
	if(row){
		$("#ff").autofill( row );
		$('#formModal').modal('show');
	}
	$('#ff').attr('action','constantType/update');
//	$('#form').form({
//		url:"user/updateUser",
//		onSubmit:function(param){
//			return $(this).form('validate');
//		}
//	});
}