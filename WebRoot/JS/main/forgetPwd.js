//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	telephone: {
    		threshold: 11,
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
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
		}
    }
}).on('success.form.bv', function(e) {
	e.preventDefault();
	var $form = $(e.target);
	$form.ajaxSubmit(function(data) {     
		if(data.status==200){
			$tds = $('#stepTable td');
			$tds.eq(1).css('border-bottom','2px solid #CCCCCC');
			$tds.eq(1).children('span').css('background-color','#CCCCCC');
			$tds.eq(2).css('border-bottom','2px solid #FE6618');
			$tds.eq(2).children('span').css('background-color','#FE6618');
			var telephone = $('#ff input[name="telephone"]').val();
			$('#ff2 input[name="telephone"]').val(telephone);
			$('#ff').hide();
			$('#ff2').show();
		}else if(data.status==500){
			alert('验证码错误');
		}
	});
});

$('#ff2').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
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
		}
    }
}).on('success.form.bv', function(e) {
	e.preventDefault();
	var $form = $(e.target);
	$form.ajaxSubmit(function(data) {     
		if(data.status==200){
			$tds = $('#stepTable td');
			$tds.eq(2).css('border-bottom','2px solid #CCCCCC');
			$tds.eq(2).children('span').css('background-color','#CCCCCC');
			$tds.eq(3).css('border-bottom','2px solid #FE6618');
			$tds.eq(3).children('span').css('color','#FE6618');
			$('#ff2').hide();
			$('#successDiv').show();
			intervalTime();
		}else if(data.status==500){
			alert('此手机号未注册');
		}
	});
});

function getSmsNum(btn){
	var $form = $(btn).parents('form');
	var $telephone = $form.children(".form-group:has(input[name='telephone'])");
	if(!$telephone.hasClass("has-success")){
		alert('请有效的手机号码');
		return;
	}
	var telephone = $("input[name='telephone']").val();
	$.get('login/getSmsNumOfPwd/'+telephone,function(data){
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

//跳转登录页面
var g_count=5; 
function intervalTime(){ 
	if(g_count == 0){ 
		window.location.href = 'login.jsp';
	}else{
		$('#intervalSec').text(g_count);
		g_count--;
		setTimeout(function() { 
			intervalTime() 
		},1000);
	}
}