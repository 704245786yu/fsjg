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
	
	$('#ff').ajaxForm(function(){
		alert('ajaxForm');
	});
});
//$('#idCardPhoto').fileupload({
//	done: function (e, data) {	//上传请求成功完成后的回调处理方法
//	}
//});
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
/*$('#ff').bootstrapValidator({
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
    	},
    	'contractor.processYear': {
    		validators: {
    			integer: {
    				message: '必须为数字'
    			}
    		}
    	},
    	'contractor.workerAmount': {
    		validators: {
    			integer: {
    				message: '必须为数字'
    			}
    		}
    	},
    	'contractor.quote': {
    		validators: {
    			stringLength: {
    				max: 30,
    				message: '最多30个字符'
    			}
    		}
    	},
    	'contractor.equipment': {
    		validators: {
    			stringLength: {
    				max: 50,
    				message: '最多50个字符'
    			}
    		}
    	},
    	'contractor.processDesc': {
    		validators: {
    			stringLength: {
    				max: 100,
    				message: '最多100个字符'
    			}
    		}
    	}
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});*/

//显示Form表单，隐藏其他面板
function showForm(){
	$('#listPanel').hide();
	$('#editPanel').show();
}

//新增，该方法由主页面的add按钮触发
function add(){
	$('#ff').attr('action','personContractor/saveData');
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