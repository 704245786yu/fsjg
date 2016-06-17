$(function(){
	initDistrictSelect('#province', null);
	$('#districtDiv select').each(function(i){
		//镇/乡/街道无需出发该事件
		if(i==3)
			return false;

		$(this).change(function(){
			//未选择则返回
			var code = $(this).val();
			if(code == "")
				return;
			
			var districtLevel = $(this).attr('id');
			var selectId = null;
			switch(districtLevel){
			case 'province':
				selectId = '#city';
				break;
			case 'city':
				selectId = '#county';
				break;
			case 'county':
				selectId = '#town';
				break;
			}
			initDistrictSelect(selectId, code);
		});
	});
});

/**初始化城市下拉框信息*/
function initDistrictSelect(selectId, pCode){
	$.get("district/getByParent",{'pCode':pCode},function(data){
		var $district = $(selectId).empty();
		$('<option>').text('--请选择--').val("").appendTo($district);
		for(var i=0; i<data.length; i++){
			$('<option>').text(data[i].districtName).val(data[i].districtCode).appendTo($district);
		}
	});
}

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	'person.realName': {
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
    	'person.province': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	'person.city': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	'person.county': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	'person.detailAddr': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 40,
    				message: '最多40个字符'
    			}
    		}
    	},
    	'person.telephone': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			phone: {
    				message: '电话号码格式不正确',
    				country: 'CN'
    			}
    		}
    	},
    	'person.email': {
    		validators: {
    			emailAddress: {
    				message: '邮箱格式不正确'
    			}
    		}
    	},
    	'person.qq': {
    		validators: {
    			integer: {
    				message: 'QQ号格式不正确'
    			}
    		}
    	},
    	'person.postalCode': {
    		validators: {
    			integer: {
    				message: '非数字'
    			}
    		}
    	},
    	'person.idCard': {
    		validators: {
    			stringLength: {
    				max: 20,
    				message: '最多20个字符'
    			}
    		}
    	}
    	/*constantTypeCode: {
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
        }*/
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