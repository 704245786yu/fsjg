$(function(){
	var userType = $(':hidden[name="userType"]').val();
	if(userType == 2){
		$('a[href="#enterprise"]').tab('show');
	}
});

$('#personForm').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	userName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				min:4,
    				max:20,
    				message: '输入4~20个字'
    			},
    			remote : {
					trigger: 'keyup',
					delay:2000,
					message: '用户名已存在',
					url:'login/nameIsExist'
				}
    		}
    	},
    	password : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '字符个数6~20'
				}
			}
		},
		rePassword : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				identical : {
					field : 'password',
					message : '确认密码与原密码不一致'
				}
			}
		},
    	telephone: {
    		threshold: 11,
    		validators: {
    			notEmpty : {
					message : '不能为空'
				},
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },
	    		remote : {
					trigger: 'keyup',
					delay:1000,
					message: '手机号已存在',
					url:'login/teleIsExist'
				}
    		}
    	},
		smsNum: {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				integer: {
                    message: '需填数字'
                }
			}
		},
		agree: {
			validators:{
				notEmpty:{
					message : '需同意后才能注册'
				}
			}
		}
    }
});

$('#enterpriseForm').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	userName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				min:4,
    				max:20,
    				message: '输入4~20个字'
    			},
    			remote : {
					trigger: 'keyup',
					delay:2000,
					message: '用户名已存在',
					url:'login/nameIsExist'
				}
    		}
    	},
    	enterpriseName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max:30,
    				message: '最多30个字'
    			},
    			remote : {
					trigger: 'keyup',
					delay:2000,
					message: '企业名称已存在',
					url:'login/enterpriseIsExist'
				}
    		}
    	},
    	password : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '字符个数6~20'
				}
			}
		},
		rePassword : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				identical : {
					field : 'password',
					message : '确认密码与原密码不一致'
				}
			}
		},
    	telephone: {
    		threshold: 11,
    		validators: {
    			notEmpty : {
					message : '不能为空'
				},
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },
	    		remote : {
					trigger: 'keyup',
					delay:1000,
					message: '手机号已存在',
					url:'login/teleIsExist'
				}
    		}
    	},
    	smsNum: {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				integer: {
                    message: '需填数字'
                }
			}
		},
		agree: {
			validators:{
				notEmpty:{
					message : '需同意后才能注册'
				}
			}
		}
    }
});


function getSmsNum(btn){
	var $form = $(btn).parents('form');
	var $telephone = $form.children(".form-group:has(input[name='telephone'])");
	if(!$telephone.hasClass("has-success")){
		alert('请有效的手机号码');
		return;
	}
	var telephone = $telephone.children("input[name='telephone']").val();
	$.get('login/getSmsNum/'+telephone,function(data){
		if(data.status==200){
			$(btn).attr('disabled','disabled');
			settime($(btn));
		}else if(data.status==500){
			alert('验证码发送失败,请重新发送');
		}
	});
}

var g_countdown=60; 
function settime($btn){ 
	if(g_countdown == 0){ 
		$btn.attr("disabled",false);    
		$btn.html("获取短信验证码"); 
		g_countdown = 60; 
	}else{ 
		$btn.html("短信已发送(" + g_countdown + ")"); 
		g_countdown--; 
		setTimeout(function() { 
			settime($btn) 
		},1000);
	}
}