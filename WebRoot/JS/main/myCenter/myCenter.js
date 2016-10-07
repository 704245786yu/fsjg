$(function(){
	var index = $(':hidden[name="index"]').val();
	if(index != ''){
		$('.menu_li li[class!="level1"]').css('color','black');
		$('.menu_li li[name="'+index+'"]').css('color','#00b8ef');
		var index = Number(index);
		var $divs = $('#mainContent > div');
		$divs.css('display','none');
		$divs[index-1].style.display = 'block';
	}
});

$('.menu_li li[class!="level1"]').click(function(){
	$('.menu_li li[class!="level1"]').css('color','black');
	$(this).css('color','#00b8ef');
	var index = Number($(this).attr('name'));
	var $divs = $('#mainContent > div');
	$divs.css('display','none');
	$divs[index-1].style.display = 'block';
});

$('#meter').entropizer({
	target:'#password',
	engine: {
        classes: ['lowercase', 'uppercase', 'numeric','symbols','symbolsCommon']
    },
    buckets: [
          { max: 45, strength: '弱', color: '#e13' },
          { min: 45, max: 60, strength: '中', color: '#f80' },
          { min: 60, max: 75, strength: '强', color: '#8c0' },
          { min: 75, strength: '极强', color: '#0c8' }
    ],
	update: function(data, ui) {
        ui.bar.css({
            'background-color': data.color,
            'width': data.percent + '%'
        });
        ui.text.html(data.strength);
    }
});

//表单重置
function resetForm(){
	$('#modifyPwdForm').bootstrapValidator('resetForm', true);
	resetMeter();
}

function resetMeter(){
	$('.entropizer-bar').css('width','0%');
	$('.entropizer-text').html('弱');
}

$('#modifyPwdForm').bootstrapValidator({
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		oldPassword : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '字符个数6~20'
				},
				remote : {
					trigger: 'keyup',
					delay:1000,
					message: '您输入的原密码有误',
					url:'basicUser/checkMyPwd',
					data: function(validator) {
						return {userId: $('#modifyPwdForm > input[name="id"]').val()};
					}
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
		}
	}
}).on('success.form.bv', function(e) {
	e.preventDefault();
	var $form = $(e.target);
    $.post($form.attr('action'), $form.serialize(), function(data) {
    	if(data == 'success'){
    		alert('密码修改成功');
    		resetForm();
    	}else
    		alert('修改失败');
    });
});