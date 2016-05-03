var g_constantType = new Object();//全局变量,常量类型
$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
	//获取字典常量类型
	$("select[name='constantTypeCode'] > option").each(function () {
        var txt = $(this).text(); //获取单个text
        var val = $(this).val(); //获取单个value
        g_constantType[val] = txt;
    });
});

function getQueryParams(params){
	params.pageSize = params.limit;
	var searchText = $('#searchText').val().trim();
	params.constantName = searchText;
	delete params.limit;
	delete params.order;
	return params;
}

//常量类型编码
function constantFormatter(value,row,index){
	return g_constantType[value];
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
    	constantName: {
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
    	constantTypeCode: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '请输入1到20个字符'
                },
                regexp: {
                    regexp: /^[a-zA-Z_]+$/,
                    message: '只能由字母和下划线组成'
                }
            }
        },
        constantValue: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 8,
                    message: '请输入1到8个字符'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '只能输入数字'
                }
            }
        },
        description: {
            validators: {
                stringLength: {
                    min: 1,
                    max: 30,
                    message: '请输入1到30个字符'
                }
            }
        }
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

//新增
function add(){
	$('#ff').attr('action','constantDict/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'constantDict/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'constantDict/delete/');
}