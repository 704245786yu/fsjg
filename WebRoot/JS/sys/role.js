var g_zTree = null;//zTree对象

var setting = {
	async:{
		enable: true,
		url:"menu/getAdjTree"
	},
	callback:{
		onAsyncSuccess:function(event,treeId,treeNode, msg){
			var node = g_zTree.getNodeByParam("path", "home", null);
			g_zTree.checkNode(node, true, true);	//默认勾选首页菜单
			g_zTree.expandAll(true);
		}
	},
	check: {
		enable: true
	},
	data:{
		simpleData:{
			enable: true,
			idKey:"id",
			pIdKey:"pId"
		},
		key:{
			name:"menuName"
		}
	}
};

$(function(){
	g_zTree = $.fn.zTree.init($("#tree"),setting);
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

//根据角色名搜索
function search(){
	var data = $('#dg').bootstrapTable('getData');
	var param = $('#searchText').val().trim();
	$('#dg').bootstrapTable('getRowsHidden',true);
	if(param != ''){
		for(var i=0; i<data.length; i++){
			var searchText = data[i].roleName;
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

/**@param value undefined
 * */
function operFormatter(value,row,index){
	var detailBtn = "<button type='button' class='btn btn-default btn-xs' title='详情' onclick='detail("+row.id+")'><span class='text-primary glyphicon glyphicon-eye-open'></span></button>";
	var modifyBtn = " <button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.id+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>";
	var delBtn = " <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.id+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
	return detailBtn+modifyBtn+delBtn;
}

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	roleName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 10,
    				message: '最多10个字符'
    			}
    		}
    	}
    }
}).on('success.form.bv', function(e) {
	submitFunc(e);
});

function submitFunc(e){
	e.preventDefault();
    var $form = $(e.target);
    var action = $form.attr('action');
    var roleName = $('#roleName').text();
    var description = $('#description').text();
    //获取勾选的菜单ID
    var checkedNodes = g_zTree.getCheckedNodes(true);
    var menuIds = new Array();
    for(var i=0; i<checkedNodes.length; i++)
    	menuIds.push(checkedNodes[i].id);
    //获取勾选的权限
    var checkboxs = $("input[name='authorityId']:checked");
    var authorityIds = new Array();
    for(var i=0; i<checkboxs.length; i++)
    	authorityIds.push(checkboxs[i].value);
    var obj = {
    		'roleName':roleName,
    		'description':description,
    		'menuIds':menuIds,
    		'authorityIds':authorityIds
    };
    //post返回的数据必须包含unique id字段
    $.post(action, obj, function(data) {
    	var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
    	if(opt.indexOf("save")!=-1){
    		$('#dg').bootstrapTable('append',data);
    	}else if(opt.indexOf("update")!=-1){	//update by unique id
    		$('#dg').bootstrapTable('updateByUniqueId',{'id':data.id,'row':data});
    	}
    	$('#formModal').modal('hide');
    },'json');
}

//新增
function add(){
	$('#ff').attr('action','role/saveRole');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'role/updateRole');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'role/delete/');
}

//==========================查看详情============================
function detail(id){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	$('#modalTitle').text(row.roleName);
	$.get("role/getMenuAndAuth/"+id,function(data){
		$('#menuDg').bootstrapTable('load',data.menuList);
		initAuthList(data.authorityList);
		$('#detailModal').modal('show');
	});
}

$('#menuDg').on('post-body.bs.table', function (e, data) {
	$(this).treegrid();//表格数据加载成功渲染树
})

//树形表格行样式
function rowStyle(value, row, index) {
	var ownClass = 'treegrid-'+value.id;
	var pClass = '';
	if(value.pId != null)
		pClass = 'treegrid-parent-'+value.pId;
	return {
		classes: ownClass+' '+pClass
	};
}

//初始化权限列表
function initAuthList(authorityList){
	var listGroup = $('#listGroup');
	$('#listGroup li:not(.list-group-item-success)').remove();
	for(var i=0; i<authorityList.length; i++){
		$('<li>',{'class':'list-group-item',text:authorityList[i].authorityName})
			.appendTo(listGroup);
	}
}
