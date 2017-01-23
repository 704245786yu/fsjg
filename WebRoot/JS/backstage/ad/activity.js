$(function(){
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
	initUEditor();
});

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	title: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 30,
    				message: '最多30个字'
    			}
    		}
    	},
    	source: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 20,
    				message: '最多20个字'
    			}
    		}
    	}
    }
}).on('success.form.bv', function(e){
	e.preventDefault();
	var $form = $(e.target);
	$form.ajaxSubmit(function(data) {     
		var action = $form.attr('action');
		var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
		if(opt.indexOf("save")!=-1){
			$('#dg').bootstrapTable('append',data);
		}else if(opt.indexOf("update")!=-1){	//update by unique id
			$('#dg').bootstrapTable('updateByUniqueId',{'id':data.id,'row':data});
		}
		cancelEdit();
	});
});

function queryParams(params){
	var title = $('input[name="title"]').val();
	var beginDate = $('input[name="beginDate"]').val();
	var endDate = $('input[name="endDate"]').val();
	params.title = title;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	delete params.order;
	return params;
}

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
		        'simpleupload',//单图上传
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

//查询
function search(){
	$('#dg').bootstrapTable('refresh');
}

//取消编辑
function cancelEdit(){
	g_bsFormTableExtend.cancelEdit();
	var ue = UE.getEditor('editor');
	ue.setContent('');
}

//新增
function add(){
	$('#ff').attr('action','activity/saveActivity');
	g_bsFormTableExtend.showAddPanel();
}

//修改
function modify(id){
	var data = $('#dg').bootstrapTable('getRowByUniqueId',id);
	fillDistrict(data.province, data.city, data.county, data.town);
	$.post('activity/getContent',{'id':id},function(data){
		g_bsFormTableExtend.showModifyPanel(id, 'activity/updateActivity');
		var ue = UE.getEditor('editor');
		ue.setContent(data);
	});
}

//删除
function del(index,id){
	var jqConfirmExtend = new JqConfirmExtend();
	if(confirm("确定要清空数据吗？")){
		$.get('activity/delete/'+id,function(result){
			if(result == 1){
				$('#dg').bootstrapTable("removeByUniqueId", id);//删除数据行
			}else
				jqConfirmExtend.autoClose();
		});
	 }
//	g_bsFormTableExtend.delRecord(index,id,'tradeNews/delete/');
}