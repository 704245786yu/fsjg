$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

function getQueryParams(params){
	var searchText = $('#searchText').val().trim();
	params.districtName = searchText;
	delete params.order;
	return params;
}

//根据常量名称搜索
function search(){
	$('#dg').bootstrapTable('selectPage',1);
}

//查询框回车执行查询操作
$('#searchText').keydown(function(event){
	if(event.keyCode == 13){
		search();
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
    	districtName: {
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
					delay:2000,
					message: '地区名已存在',
					url:'district/nameIsExist'
				}
    		}
    	},
    	districtCode: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                	max: 12,
    				message: '最多12个字符'
                },
                digits:{
                	message:'必须为整数'
                },
                remote : {
					trigger: 'keyup',
					delay:2000,
					message: '编码已存在',
					url:'district/codeIsExist'
				}
            }
        }
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

function operFormatter(value,row,index){
	return "<button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.districtCode+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>" +
			" <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.districtCode+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
}

//新增
function add(){
	$('#ff').attr('action','district/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'district/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'district/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		var result = data.result;
		if(result.status == 200){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
			var pageNumber = opt.pageNumber;
			$('#dg').bootstrapTable('selectPage',pageNumber);
		}else if(result.status == 500){
			alert("第"+JSON.stringify(result.value)+"数据有问题");
		}else if(result.status == 501){
			alert("文件上传异常");
		}
	}
});