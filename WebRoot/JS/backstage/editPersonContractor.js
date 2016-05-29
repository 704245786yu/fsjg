//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	constantName: {
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
        constantValue: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 8,
                    message: '请输入1到8个字符'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '只能输入数字'
                }
            }
        },
        description: {
            validators: {
                stringLength: {
                    min: 1,
                    max: 30,
                    message: '请输入1到30个字符'
                }
            }
        }
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

//显示Form表单，隐藏其他面板
function showForm(){
	$('#listPanel').hide();
	$('#editPanel').show();
}

//新增，该方法由主页面的add按钮触发
function add(){
	$('#ff').attr('action','personContractor/save');
	showForm();
}

//新增，该方法由主页面的add按钮触发
function modify(id){
	$.get('personContractor/getById/'+id, function(data){
		$("#ff").fill(data.person);
		$("#ff").fill(data.contractor);
		$('#ff').attr('action','personContractor/update');
		showForm();
	});
}

/**取消编辑表单，同时重置表单
 * */
function cancel(){
	$('#listPanel').show();
	$('#editPanel').hide();
	var $form = $('#ff');
	$form.bootstrapValidator('resetForm', true);
	$form[0].reset();
}