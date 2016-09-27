var g_jqConfirm = new JqConfirmExtend();

$(function(){
	var province = $('input[name="province"]').val();
	var city = $('input[name="city"]').val();
	var county = $('input[name="county"]').val();
	var town = $('input[name="town"]').val();
	fillDistrict(province, city, county, town);
	//显示图片
	var idFrontPhoto = $('input[name="idFrontPhoto"]').val();
	var idBackPhoto = $('input[name="idBackPhoto"]').val();
	if(idFrontPhoto!=null && idFrontPhoto!=''){
		var $div = $('input[name="idFrontPhoto"] ~ div').css('display','');
		$div.children('img').attr('src','uploadFile/person/'+idFrontPhoto);
	}
	if(idBackPhoto!=null && idBackPhoto!=''){
		var $div = $('input[name="idBackPhoto"] ~ div').css('display','');
		$div.children('img').attr('src','uploadFile/person/'+idBackPhoto);
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
    	'basicUser.userName': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 20,
    				message: '最多20个字符'
    			},
    			remote : {
					trigger: 'keyup',
					delay:1000,
					message: '手机号已存在',
					url:'basicUser/isNameExist'
				}
    		}
    	},
    	realName: {
    		validators: {
    			stringLength: {
    				max: 10,
    				message: '最多10个字符'
    			}
    		}
    	},
    	'basicUser.telephone': {
    		threshold: 11,
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },
                remote : {
					trigger: 'keyup',
					delay:1000,
					message: '手机号已存在',
					url:'basicUser/isTeleExist'
				}
    		}
    	},
    	province:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	city:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	county:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	detailAddr:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 50,
    				message: '最多50个字符'
    			}
    		}
    	},
    	qq:{
    		validators: {
    			digits:{
    				message:'必须为数字'
    			}
    		}
    	}
    }
});

$('#ff').ajaxForm(function(data){
	if(data.status==200){
		g_jqConfirm.autoClose('更新成功');
		$('#ff').bootstrapValidator('resetForm');
		var value = data.value;
		//更新用户基本信息
		updateBasicInfo(data.value);
		//显示图片
		if(value.idFrontPhoto!=null && value.idFrontPhoto!=''){
			var $div = $('input[name="idFrontPhoto"] ~ div').css('display','');
			$div.children('img').attr('src','uploadFile/person/'+value.idFrontPhoto);
		}
		if(value.idBackPhoto!=null && value.idBackPhoto!=''){
			var $div = $('input[name="idBackPhoto"] ~ div').css('display','');
			$div.children('img').attr('src','uploadFile/person/'+value.idBackPhoto);
		}
	}else if(data.status==500){
		g_jqConfirm.showDialog('保存失败',data.value);
	}else if(data.status==501){
		g_jqConfirm.showDialog('保存失败',data.value);
	}
});

//更新用户基本信息
function updateBasicInfo(data){
	var $table = $('#basic-info');
	$table.find('span[name="userName"]').html(data.basicUser.userName);
	$table.find('span[name="realName"]').html(data.realName);
	$table.find('span[name="email"]').html(data.email);
	$table.find('span[name="telephone"]').html(data.telephone);
	$table.find('span[name="idNum"]').html(data.idNum);
}

//上传文件验证,不兼容IE9及以下浏览器
function imgChange(file,maxSize){
	//image/jpeg image/png
	var files = file.files;
	//IE9以下无此属性
	if(files==null)
		return;
	var f = files[0];
	if(f.type!='image/jpeg' && f.type!='image/png'){
		g_jqConfirm.autoClose("请上传jpg或png图片");
		return;
	}else if(f.size > (maxSize*1000)){
		g_jqConfirm.autoClose("上传的图片大于"+maxSize);
		return;
	}
}

/**同时重置表单
 * */
//function resetForm(){
//	var $form = $('#ff');
//	$form.bootstrapValidator('resetForm', true);
//	$form[0].reset();
//}