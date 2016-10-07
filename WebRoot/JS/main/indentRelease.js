$(function(){
	initUEditor();
	initFileUpload();
	//初始化查询日期
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
	var val = $(effectiveDateSel).val();
	var str = moment().add('days',val).format('YYYY-MM-DD');
	$('#effectiveDateTxt').text(str);
	$(':hidden[name="effectiveDate"]').text(str);
});

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	indentType: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	indentName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 12,
    				message: '最多12个字符'
    			}
    		}
    	},
    	/*'basicUser.telephone': {
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
    	}*/
    }
});

function initUEditor(){
	var ue = UE.getEditor('editor', {
	    autoFloatEnabled: true,
	    toolbars: [
		    [
		        'undo', //撤销
		        'redo', //重做
		        'bold', //加粗
		        'italic', //斜体
		        'underline', //下划线
		        'subscript', //下标
		        'superscript', //上标
		        'formatmatch', //格式刷
		        'pasteplain', //纯文本粘贴模式
		        'preview', //预览
		        'horizontal', //分隔线
		        'removeformat', //清除格式
		        'unlink', //取消链接
		        'cleardoc', //清空文档
		        ],
		        [
		        'fontfamily', //字体
		        'fontsize', //字号
		        'paragraph', //段落格式
		        'link', //超链接
		        'searchreplace', //查询替换
		        'justifyleft', //居左对齐
		        'justifyright', //居右对齐
		        'justifycenter', //居中对齐
		        'justifyjustify', //两端对齐
		        'forecolor', //字体颜色
		        'backcolor', //背景色
		        'insertorderedlist', //有序列表
		        'insertunorderedlist', //无序列表
		        'rowspacingtop', //段前距
		        'rowspacingbottom', //段后距
		        'lineheight', //行间距
		        'edittip ' //编辑提示
		    ]
		]
	});
}

//初始化上传文件控件
function initFileUpload(){
	//上传图片
	$('#fileUploadImg').fileupload({
		url:'indent/uploadImg',
		submit:function(e, data){
			var file = data.files[0];
			var fileName = file.name;
			var size = file.size;
			var type = file.type;
			if(type!="image/png" && type!="image/jpeg"){
				new JqConfirmExtend().autoClose("请上传jpg或png格式图片");
				return false;
			}
			if(size>200000){
				new JqConfirmExtend().autoClose("图片不能大于200KB");
				return false;
			}
		},
		done : function(e, data) { // 上传请求成功完成后的回调处理方法
			var result = data.result;
			//设置图片的实际文件名称，多个图片之间用,隔开
			if(result.status == 200){
				var photo = $('input[name="photo"]');
				if(photo.val() == '')
					photo.val(result.value);
				else
					photo.val(photo.val()+','+result.value);
				
				//将data参数中上下文数据插入到#files元素
				var $imgNames = $('#imgNames');
				$.each(data.files,function(index,file){
					//遍历上传图片文件列表并插入文件列表
					$imgNames.append($('<div>').text(file.name));
				});
			}else if(result.status == 500){
				if(result.value=='nologin'){
					new JqConfirmExtend().autoClose('请先登录到平台');
				}else if(result.value=='imageError'){
					new JqConfirmExtend().autoClose('图片格式不对');
				}
			}else if(result.status == 501){
				new JqConfirmExtend().autoClose('上传失败');
			}
		}
	});
	//上传附件
	$('#fileUploadDoc').fileupload({
		url:'indent/uploadDoc',
		submit:function(e, data){
			var file = data.files[0];
			var fileName = file.name;
			var size = file.size;
			var ary = fileName.split(".");
			var suffix = ary[ary.length-1];
			if(suffix!="txt" && suffix!="doc" && suffix!="docx" && suffix!="xls" && suffix!="xlsx"){
				new JqConfirmExtend().autoClose("请上传txt、word或excel格式文档");
				return false;
			}
			if(size>500000){
				new JqConfirmExtend().autoClose("文件不能大于500KB");
				return false;
			}
		},
		done : function(e, data) { // 上传请求成功完成后的回调处理方法
			var result = data.result;
			//设置文档的实际名称
			if(result.status == 200){
				var doc = $('input[name="document"]');
				doc.val(result.value);
				
				//将data参数中上下文数据插入到#files元素
				$.each(data.files,function(index,file){
					//遍历上传文件列表并插入文件列表
					$('#docName').html(file.name);
				});
			}else if(result.status == 500){
				if(result.value=='nologin'){
					new JqConfirmExtend().autoClose('请先登录到平台');
				}else if(result.value=='docError'){
					new JqConfirmExtend().autoClose('文档格式不对');
				}
			}else if(result.status == 501){
				new JqConfirmExtend().autoClose('上传失败');
			}
		}
	});
}

//确定选择产品类别
function checkCostume(){
	var str = '';
	var val = '';
	var $checks = $('#costumeCategoryModal :checked').not(':disabled');
	for(var i=0; i<$checks.length; i++){
		var $check = $($checks[i]);
		val += $check.val()+',';
		str += $check.next().html()+',';
	}
	if(str==''){
		str = '选择产品类别';
	}else{
		val = val.substring(0, val.length-1);
	}
	$('#costumeBtn').html(str);
	$(':hidden[name="costumeCode"]').val(val);
}

//确定选择地区
function checkDistrict(){
	var $select = $('#districtContainer :selected');
	var str = '';
	for(var i=0; i<$select.length; i++){
		if($($select[i]).val() != '')
			str += $($select[i]).text()+' ';
	}
	if(str == '')
		str = '选择发单地区';
	$('#districtBtn').html(str);
}

$(':checkbox[name="expectPrice"]').change(function(){
	if($(this).prop('checked'))
		$(':text[name="expectPrice"]').val('').prop('disabled','disabled');
	else
		$(':text[name="expectPrice"]').prop('disabled',false);
});

$('#effectiveDateSel').change(function(){
	var val = $(this).val();
	var str = moment().add('days',val).format('YYYY-MM-DD');
	$('#effectiveDateTxt').text(str);
	$(':hidden[name="effectiveDate"]').text(str);
});