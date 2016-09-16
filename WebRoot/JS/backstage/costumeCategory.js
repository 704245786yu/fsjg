$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

$(document).ajaxError(function(e,jqxhr,settings,errorMsg){
	console.log(jqxhr.status);
	console.log(jqxhr.statusText);
	console.log(jqxhr.responseText);
	alert("发生错误，请与管理员联系");
});

function rowStyle(value, row, index) {
	var ownClass = 'treegrid-'+value.id;
	var pClass = '';
	if(value.pId != null)
		pClass = 'treegrid-parent-'+value.pId;
	return {
		classes: ownClass+' '+pClass
	};
}

/**@param value undefined
 * */
function operFormatter(value,row,index){
	var delBtn = '';
	if(row.pId!=null)//根节点无删除节点
		delBtn = " <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.id+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
	return "<button type='button' class='btn btn-default btn-xs' title='添加' onclick='add("+row.id+","+row.pId+")'><span class='text-primary glyphicon glyphicon-plus'></span></button>" +
	" <button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.id+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>" +
	delBtn;
}

$('#dg').bootstrapTable({
	onLoadSuccess: function (data) {
		$('#dg').treegrid();//表格数据加载成功渲染树
    }
});

//根据常量名称搜索
function search(){
	var data = $('#dg').bootstrapTable('getData');
	var param = $('#searchText').val().trim();
	$('#dg').bootstrapTable('getRowsHidden',true);
	if(param != ''){
		for(var i=0; i<data.length; i++){
			var searchText = data[i].categoryName;
			if(searchText.indexOf(param,0) == -1)
				$('#dg').bootstrapTable('hideRow',{index:i});
		}
	}
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
    	categoryName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max:20,
    				message: '不能超过20个字'
    			},
    		}
    	},
    	categoryCode: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			integer: {
                    message: '需填数字'
                }
    		}
    	}
    }
}).on('success.form.bv', function(e) {
	submitFunc(e);
});

function submitFunc(e,datagridId,formModalId){
	e.preventDefault();
	var $form = $(e.target);
	var action = $form.attr('action')
	
	//设置datagrid、formModal的id
	datagridId = datagridId == undefined ? '#dg':'#'+datagridId;
	formModalId = formModalId == undefined ? '#formModal':'#'+formModal;
	
	//post返回的数据必须包含unique id字段
	$.post($form.attr('action'), $form.serialize(), function(data) {
		var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
		if(opt.indexOf("save")!=-1){
			//根据父节点获取后代节点的最后一个叶子节点Id
			var lastLeafNodeId = getLastDescendantLeaf(data.pId);
			var index = $("#dg tr[data-uniqueid='"+lastLeafNodeId+"']").attr('data-index');
			$(datagridId).bootstrapTable('insertRow',{'index':index+1,'row':data});
		}else if(opt.indexOf("update")!=-1){	//update by unique id
			$(datagridId).bootstrapTable('updateByUniqueId',{'id':data.id,'row':data});
		}
		$('#dg').treegrid();//Bootstrap在新增\更新行后会重新绘制表格，导致树表失效，需重新渲染树表。
		$(formModalId).modal('hide');
	},'json');
}

//新增
function add(id,pId){
	$('#newNodePos').css('display','block');
	var select = $('#pId').empty();
	var opt1=$('<option>作为子节点</option>').val(id);
	select.append(opt1);
	if(pId != null){
		var opt2=$('<option>作为同级节点</option>').val(pId);
		select.append(opt2);
	}
	$('#ff').attr('action','costumeCategory/save');
	$('#formModal').modal('show');//显示form模态框
}


//修改
function modify(id){
	$('#newNodePos').css('display','none');
	new BsFormTableExtend().showModifyForm(id, 'costumeCategory/update');
}

//删除
function del(index,id){
	var action = 'costumeCategory/delete/';
	var title = null;
	var content = null;
	var icon = null;
	if(!$('.treegrid-'+id).treegrid('isLeaf')){
		title = '注意'
		content = '该服饰类型下的所有子类型会一并删除，是否继续？';
		icon = 'warning';
	}
	var jqConfirmExtend = new JqConfirmExtend();
	jqConfirmExtend.delConfirm(index, function(){
		$.get(action + id,function(result){
			if(result == 1){
				delRecursion(id);
				$('#dg').treegrid();
			}else
				jqConfirmExtend.autoClose();
		});
	},title,content,icon);
}

/**获取后代节点的最后一个叶子节点
 * @param id
 * */
function getLastDescendantLeaf(id){
	if($('.treegrid-'+id).treegrid('isLeaf')){
		return id;
	}else{
		var treeNodes = $('.treegrid-'+id).treegrid('getChildNodes');
		var lastTreeNode = treeNodes.get(treeNodes.length-1);
		var subId = $(lastTreeNode).treegrid('getNodeId');
		return getLastDescendantLeaf(subId);
	}
}

/**递归删除节点
 * */
function delRecursion(id){
	//判断是否是叶子节点，不是则先删除子节点
	if(!$('.treegrid-'+id).treegrid('isLeaf')){
		var treeNodes = $('.treegrid-'+id).treegrid('getChildNodes');
		for(var i=0; i<treeNodes.length; i++){
			var treeNode = treeNodes.get(i);
			var subId = $(treeNode).treegrid('getNodeId');
			delRecursion(subId);
		}
	}
	$('#dg').bootstrapTable("removeByUniqueId", id);//删除数据行
}