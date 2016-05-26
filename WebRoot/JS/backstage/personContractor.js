var g_processType = new Object();
$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
	
	//初始化g_processType，供table的processType格式化显示用
	var processTypes = $('#processType option');
	$.each(processTypes, function(i,n){
		g_processType[n.value] = n.text; 
	});
	$('input[name="daterange"]').daterangepicker({
//		maxDate: new Date(),
		timePicker: true,
		timePicker24Hour:true,
        timePickerIncrement: 1,
		locale:{
			format:'YYYY-MM-DD HH:mm',
			applyLabel: '确定',
			cancelLabel: '取消'
		}
	});
});

function getQueryParams(params){
	params.pageSize = params.limit;
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
	delete params.limit;
	delete params.order;
	return params;
}

function view(id){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	var pAry = $('#viewModal p');
	for(var i=0; i<pAry.length; i++){
		var $p = $(pAry[i]);
		var name = $p.attr("name");
		console.log(name);
		$p.html(row[name]);
	}
	$('#viewModal').modal('show');//显示form模态框
}

//加工类型
function processFormatter(value,row,index){
	return g_processType[value];
}

//日期格式化
function dateFormatter(value,row,index){
	return new Date(value).format("yyyy-MM-dd hh:mm:ss");
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
	$('#ff').attr('action','person/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'person/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'person/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		if(data.result == 1){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
//		  		var pageSize = opt.pageSize;
			var pageNumber = opt.pageNumber;
			console.log(pageNumber);
//		  		var totalRows = opt.totalRows;
//		  		var tzPageNumber = Math.ceil(totalRows/pageSize);
			$('#dg').bootstrapTable('selectPage',pageNumber);
		}else{
			alert('上传文件失败');
		}
	}
});