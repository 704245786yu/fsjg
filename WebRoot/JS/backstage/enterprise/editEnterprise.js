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
    	}
    }
});

var options = {  
   beforeSubmit: beforeSubmit,  //提交前的回调函数  
   success: success      //提交后的回调函数  
}

$('#ff').ajaxForm(options);

function beforeSubmit(formData, jqForm, options){
	formData.push({'name':'delImg','value':"xxx.jsp"});
	formData.push({'name':'delImg','value':"xxx2.jsp"});
}

function success(data){
	var action = $('#ff').attr('action');
	if(data.status==200){
		var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
		if(opt.indexOf("save")!=-1){
			$('#dg').bootstrapTable('append',data);
		}else if(opt.indexOf("update")!=-1){	//update by unique id
			$('#dg').bootstrapTable('updateByUniqueId',{'id':data.id,'row':data.value});
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
function modify(id){
	var data = $('#dg').bootstrapTable('getRowByUniqueId',id);
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
	$('#ff').attr('action','enterprise/update');
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
	var imgs = $(':hidden[name="enterpriseImg"]').val().split(',');
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
	$('#listPanel').show();
	$('#editPanel').hide();
	g_delImg = new Array();
	var $form = $('#ff');
	
	$form.find('input[name="logoImg"] ~ div').css('display','none');//隐藏工厂logo
	$form.find('input[name="licensePic"] ~ div').css('display','none');//licensePic工厂营业执照
	$form.find(':hidden[name="enterpriseImg"] ~ div:first').nextAll().remove();//移除展示的工厂图片
	$form.bootstrapValidator('resetForm', true);
	$form[0].reset();
}