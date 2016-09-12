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
					delay:1000,
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
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },
	    		remote : {
					trigger: 'keyup',
					delay:2000,
					message: '手机号已存在',
					url:'login/teleIsExist'
				}
    		}
    	},
		smsNum: {
			validators : {
				notEmpty : {
					message : '不能为空'
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
					delay:1000,
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
					delay:1000,
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
    		validators: {
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },
	    		remote : {
					trigger: 'keyup',
					delay:2000,
					message: '手机号已存在',
					url:'login/teleIsExist'
				}
    		}
    	},
    	smsNum: {
			validators : {
				notEmpty : {
					message : '不能为空'
				}
			}
		}
    }
});


function getSmsNum(){
	var bootstrapValidator = $('#personForm').data('bootstrapValidator');
	var tele = bootstrapValidator.getFieldElements('telephone');
	console.log(tele);
}