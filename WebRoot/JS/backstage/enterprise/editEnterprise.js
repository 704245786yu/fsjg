var g_jqConfirm = new JqConfirmExtend();
var g_delImg = new Array();

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
    			}
    		}
    	},
    	enterpriseName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 30,
    				message: '最多30个字符'
    			}
    		}
    	},
    	linkman: {
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
    	'basicUser.telephone': {
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
    	trade:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	processType:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	saleMarket:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	staffNumber:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			},
    			integer:{
    				message:'必须为数字'
    			}
    		}
    	}
    }
}).on('success.form.bv', function(e) {
	if(!isCostumeCheck()){
		e.preventDefault();
		var $form = $(e.target);
		$form.find(':submit').removeAttr('disabled');
		alert('请选择主营产品');
	}
});

var options = {  
   beforeSubmit: beforeSubmit,  //提交前的回调函数  
   success: success      //提交后的回调函数  
}

$('#ff').ajaxForm(options);

function beforeSubmit(formData, jqForm, options){
	for(var i=0; i<g_delImg.length; i++){
		formData.push({'name':'delImg','value':g_delImg[i]});
	}
}

function success(data){
	var action = $('#ff').attr('action');
	if(data.status==200){
		var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
		if(opt.indexOf("save")!=-1){
			$('#dg').bootstrapTable('append',data.value);
		}else if(opt.indexOf("update")!=-1){	//update by unique id
			$('#dg').bootstrapTable('updateByUniqueId',{'id':data.value.id,'row':data.value});
		}
		cancel();
	}else if(data.status==500){
		g_jqConfirm.showDialog('保存失败',data.value);
	}else if(data.status==501){
		g_jqConfirm.showDialog('保存失败',data.value);
	}
}

//显示Form表单，隐藏其他面板
function showForm(){
	g_delImg = new Array();
	$('#listPanel').hide();
	$('#editPanel').show();
}

//新增，该方法由主页面的add按钮触发
function add(){
	$('#ff').attr('action','enterprise/saveEnterprise');
	showForm();
}

//新增，该方法由主页面的add按钮触发
var g_basicUserId = null;
function modify(id){
	var data = $('#dg').bootstrapTable('getRowByUniqueId',id);
	g_basicUserId = data.basicUser.id;
	$("#ff").autofill(data);
	checkCostumeByCodes(data.costumeCode);//设置“选择产品类别”button的显示文字
	
	fillDistrict(data.province, data.city, data.county, data.town);
	$('input[name="basicUser.id"]').val(data.basicUser.id);
	$('input[name="basicUser.userName"]').val(data.basicUser.userName);
	$('input[name="basicUser.telephone"]').val(data.basicUser.telephone);
	//填充行业分类
	var trade = data.trade.split(',');
	for(var i=0; i<trade.length; i++){
		$(':checkbox[name="trade"][value="'+trade[i]+'"]').prop('checked','checked');
	}
	//填充加工类型
	var processType = data.processType.split(',');
	for(var i=0; i<trade.length; i++){
		$(':checkbox[name="processType"][value="'+processType[i]+'"]').prop('checked','checked');
	}
	//显示图片
	if(data.logo!='' && data.logo!='default_logo.png'){
		var $div = $('input[name="logoImg"] ~ div').css('display','');
		$div.children('img').attr('src','uploadFile/enterprise/'+data.logo);
	}
	if(data.licenseImg !=null && data.licenseImg!=''){
		var $div = $('input[name="licensePic"] ~ div').css('display','');
		$div.children('img').attr('src','uploadFile/enterprise/'+data.licenseImg);
	}
	if(data.enterpriseImg!=null && data.enterpriseImg!=''){
		var imgs = data.enterpriseImg.split(',');
		var $div = $('input[name="enterprisePic"] ~ div');
		for(var i=0; i<imgs.length; i++){
			var $divTemp = $div.clone().css('display','');
			$divTemp.children('img').attr('src','uploadFile/enterprise/'+imgs[i]);
			$div.after($divTemp);
		}
	}
	$('#ff').attr('action','enterprise/updateEnterprise');
	showForm();
}

function delImg(imgName){
	var $hidden = $(':hidden[name="'+imgName+'"]');
	g_delImg.push($hidden.val());
	$hidden.val('');
	$hidden.nextAll('div').css('display','none');
}

function delEnterpriseImg(btn){
	var $enterpriseImg = $(':hidden[name="enterpriseImg"]');
	var enterpriseImgs = $enterpriseImg.val().split(',');
	var path = "uploadFile/enterprise/";
	var $img = $(btn).parent('div').prev();
	var src = $img.attr('src')
	src = src.slice(path.length,src.length);
	g_delImg.push(src);
	var index = $.inArray(src,enterpriseImgs);
	enterpriseImgs.splice(index,1);
	$enterpriseImg.val(enterpriseImgs.join());
	$img.parent('div').css('display','none');
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

function enterpriseImgChange(file,maxSize){
	//image/jpeg image/png
	var imgStr = $(':hidden[name="enterpriseImg"]').val();
	var imgs = [];
	if(imgStr != '')
		imgs = imgStr.split(',');
	var files = file.files;
	//IE9以下无此属性
	if(files==null)
		return;
	if((files.length+imgs.length) > 6){
		g_jqConfirm.autoClose("最多选择6张图片");
		return;
	}
	for(var i=0; i<files.length; i++){
		var f = files[i];
		if(f.type!='image/jpeg' && f.type!='image/png'){
			g_jqConfirm.autoClose("请上传jpg或png图片");
			return;
		}else if(f.size > (maxSize*1000)){
			g_jqConfirm.autoClose("上传的图片大于"+maxSize);
			return;
		}
	}
}

/**取消编辑表单，同时重置表单
 * */
function cancel(){
	g_delImg = new Array();
	var $form = $('#ff');
	$form.find('input[name="logoImg"] ~ div').css('display','none');//隐藏工厂logo
	$form.find('input[name="licensePic"] ~ div').css('display','none');//licensePic工厂营业执照
	$form.find(':hidden[name="enterpriseImg"] ~ div:first').nextAll().remove();//移除展示的工厂图片
	$form.find('input[type="hidden"]').val('');
	$form.bootstrapValidator('resetForm', true);
	resetModal();//重置costumeCategoryModal
	$form[0].reset();
	$('#listPanel').show();
	$('#editPanel').hide();
}