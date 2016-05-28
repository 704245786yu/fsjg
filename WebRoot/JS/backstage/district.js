
$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
	//获取字典常量类型
	/*$("select[name='constantTypeCode'] > option").each(function () {
        var txt = $(this).text(); //获取单个text
        var val = $(this).val(); //获取单个value
        g_constantType[val] = txt;
    });*/
});

function getQueryParams(params){
	params.pageSize = params.limit;
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
	delete params.limit;
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
    			}
    		}
    	},
    	districtCode: {
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
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

function operFormatter(value,row,index){
	return "<button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.districtCode+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>" +
			" <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.districtCode+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
}

//新增
function add(){
	$('#ff').attr('action','disctrict/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'disctrict/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'disctrict/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		if(data.result == 1){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
			var pageNumber = opt.pageNumber;
			$('#dg').bootstrapTable('selectPage',pageNumber);
		}else{
			alert('上传文件失败');
		}
	}
});