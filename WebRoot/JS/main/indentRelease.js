$(function(){
	initCostumeCategory();
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

$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
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
    	}
    }
});

function initCostumeCategory(){
	var $tableTemplate = $('#template').clone();
	$tableTemplate.css('display','table');
	var $trTemplate = $tableTemplate.find('tr').clone();
	$trTemplate.css('display','table-row');
	var $tds = $trTemplate.find('td');
	//三级类目的checkbox
	var $td2 = $($tds[1]);
	var $checkbox2 = $td2.find('label').clone();
	$checkbox2.css('display','block');
	
	$.get('costumeCategory/getAllHierarchy',function(data){
		var ary = new TreeUtil().adjTransToNest(data);
		var costumeCategorys = ary[0].children;
		for(var i=0; i<costumeCategorys.length; i++){	//遍历一级类目
			var oneLevelNode = costumeCategorys[i];
			var $costumeDiv = $('#costume_'+oneLevelNode.categoryCode);
			var $table = $tableTemplate.clone();
			$costumeDiv.append($table);
			var twoLevel = oneLevelNode.children;
			var $tr = $trTemplate.clone();
			var $td2 = $($tr.find('td')[1]);
			$table.append($tr);
			for(var j=0; j<twoLevel.length; j++){	//遍历二级类目
				var twoLevelNode = twoLevel[j];
				if(twoLevelNode.children == null){	//无子节点（即三级类目）则直接添加到div中
					var $checkBox = $checkbox2.clone();
					$checkBox.find(':checkbox').val(twoLevelNode.categoryCode);
					$checkBox.find('span').html(twoLevelNode.categoryName);
					$td2.append($checkBox);
				}else{
					//二级类目节点
					var $tr = $trTemplate.clone();
					var $tds = $tr.find('td');
					var $td1 = $($tds[0]).css('display','table-cell');
					$td1.find(':checkbox').val(twoLevelNode.categoryCode);
					$td1.find('span').html(twoLevelNode.categoryName);
					//三级类目节点
					$td2 = $($tds[1]);
					var threeLevel = twoLevelNode.children;
					for(var k=0; k<threeLevel.length; k++){	//遍历三级类目
						var $checkBox = $checkbox2.clone();
						$checkBox.find(':checkbox').val(threeLevel[k].categoryCode);
						$checkBox.find('span').html(threeLevel[k].categoryName);
						$td2.append($checkBox);
					}
					$table.append($tr);
				}
			}
			//消除“服装”标签下的多出的一个空tr，该tr由32行产生
			var $trs = $table.find('tr');
			if($trs.length >2)
				$($trs[1]).remove();
		}
	});
}

//二级服饰类目单击事件
function checkAllSubBox(checkbox){
	//选中超过3个checkbox则不允许再选，直接返回
	if(isOverChecked(checkbox)==false)
		return;
	var $checkbox = $(checkbox);
	var $td1 = $checkbox.parents('td');
	var $td2 = $td1.next();
	if($checkbox.prop('checked')==true)
		$td2.find(':checkbox').prop({'checked':'checked','disabled':'disabled'});
	else
		$td2.find(':checkbox').prop({'checked':false,'disabled':false});
}

//三级类目单击事件
function threeLevelCheck(checkbox){
	isOverChecked(checkbox)
}

//判断checkbox是否选择超过三个
function isOverChecked(checkbox){
	var $checkbox = $(checkbox);
	if($checkbox.prop('checked')==true){
		var length = $('#costumeCategoryModal :checked').not(':disabled').length;
		if(length > 3){
			alert('超过三个');
			$checkbox.prop('checked',false);
			return false;
		}
	}
	return true;
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