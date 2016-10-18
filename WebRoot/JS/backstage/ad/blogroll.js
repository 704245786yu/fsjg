$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	name: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 15,
    				message: '最多15个字'
    			}
    		}
    	},
    	url: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    max: 30,
                    message: '最多30个字'
                },
                uri: {
                    message: '请输入正确的链接地址'
                }
            }
        }
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

//新增
function add(){
	$('#ff').attr('action','blogroll/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'blogroll/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'blogroll/delete/');
}