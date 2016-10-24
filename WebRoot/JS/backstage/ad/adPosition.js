var g_linkType = ['外部网址','工厂详情页'];
var g_jqConfirm = new JqConfirmExtend();
//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	sort: {
    		validators: {
    			integer: {
    				message: '必须为数字'
    			}
    		}
    	},
    	link: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	title: {
    		validators: {
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
		if(data.status==501){
			g_jqConfirm.autoClose(data.value);
		}else if(data.status==200){
			var action = $form.attr('action');
			var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
			if(opt.indexOf("save")!=-1){
				$('#dg').bootstrapTable('append',data.value);
			}else if(opt.indexOf("update")!=-1){	//update by unique id
				$('#dg').bootstrapTable('updateByUniqueId',{'id':data.value.id,'row':data.value});
			}
			cancelEdit();
		}
	});
});

//取消编辑
function cancelEdit(){
	g_bsFormTableExtend.cancelEdit();
}

function operFormatter(value,row,index){
	return "<button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.id+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>";
}

function linkTypeFmt(value,row,index){
	return g_linkType[value];
}

function imgFmt(value,row,index){
	if(value==null || value=='')
		return '';
	var $img = $('<img>').css({'width':'130px','height':'20px'});
	$img.attr('src','uploadFile/ad/'+value);
	return $img[0].outerHTML.toString();
}

//修改
function modify(id){
	var data = g_bsFormTableExtend.showModifyPanel(id, 'adPosition/updateAdPosition');
	var img = data.img;
	if(img!=null && img!=''){
		$('#editPanel img').attr('src','uploadFile/ad/'+img);
	}
}