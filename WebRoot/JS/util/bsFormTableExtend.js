/**@author 支煜
 * 2016-03
 * Bootstrap table和form插件扩展，使用于简单通用的表单增删改查业务操作
 * 此脚本依赖于bootstrap Modal、bootstarp validator、jquery.formautofill.js、jquery-confirm.js
 */
function BsFormTableExtend(){
}

/**显示修改表单模态框。表单要加载的数据来自Bootstrap table插件。表单的模态框来自Bootstrap Modal插件。
 * @param {Number,required} id form要填充数据Id,必要参数
 * @param {String,required} action form表单要提交的地址
 * @param {String} datagridId form要填充数据所在的datagrid，默认值：dg
 * @param {String} formId 默认值ff
 * @param {String} formModalId form所在模态框Id，默认值formModal
 * */
BsFormTableExtend.prototype.showModifyForm = function(id, action, datagridId, formId, formModalId){
	//设置datagrid、form、formModal的id
	var dg = datagridId == undefined ? 'dg':datagridId;
	var ff = formId == undefined ? 'ff':formId;
	var formModal = formModalId == undefined ? 'formModal':formModal;
//	,{findbyname:true,restrict:true}
	//根据Id获取对应行数据
	var data = $('#'+dg).bootstrapTable('getRowByUniqueId',id);
	$('#'+ff).attr('action',action);//设置form表单action
	$('#'+ff).autofill( data,{restrict:true} );//填充form表单
	$('#'+formModal).modal('show');//显示form模态框
}

/**form submit 执行save/update操作，更新数据表格。
 * @param {event} e bootstrapValidator验证通过事件
 * @param {Number} datagridId 数据表格Id. default value:dg
 * @param {Number} formModalId form所在模态框Id. default value:formModal
 * */
BsFormTableExtend.prototype.submitFunc = function(e,datagridId,formModalId){
	// Prevent form submission
    e.preventDefault();
    // Get the form instance
    var $form = $(e.target);
    var action = $form.attr('action')
    
    //设置datagrid、formModal的id
    datagridId = datagridId == undefined ? '#dg':'#'+datagridId;
    formModalId = formModalId == undefined ? '#formModal':'#'+formModal;
    
    //post返回的数据必须包含unique id字段
    $.post($form.attr('action'), $form.serialize(), function(data) {
    	var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
    	if(opt.indexOf("save")!=-1){
    		$(datagridId).bootstrapTable('append',data);
    	}else if(opt.indexOf("update")!=-1){	//update by unique id
    		$(datagridId).bootstrapTable('updateByUniqueId',{'id':data.id,'row':data});
    	}
    	$(formModalId).modal('hide');
    },'json');
}

/**删除记录
 * @param {Number,required} index 记录在数据表格中的行索引号
 * @param {Number,required} id 要删除的数据主键id
 * @param {Number,required} action 删除操作地址
 * @param {Number} datagridId 数据表格Id. default value:dg
 * */
BsFormTableExtend.prototype.delRecord = function(index,id,action,datagridId){
	//设置datagrid
    datagridId = datagridId == undefined ? '#dg':'#'+datagridId;
    
    //显示confirm弹出框
	var jqConfirmExtend = new JqConfirmExtend();
	jqConfirmExtend.delConfirm(index, function(){
		$.get(action + id,function(result){
			if(result == 1){
				$(datagridId).bootstrapTable("removeByUniqueId", id);//删除数据行
			}else
				jqConfirmExtend.autoClose();
		});
	});
}

/**form模态框关闭事件，触发该事件时重置form
 * @param {Number} formModalId form所在模态框Id. default value:formModal
 * @param othreFun 关闭Form表单窗口的其他操作
 * */
BsFormTableExtend.prototype.closeFormModal = function(formModalId,otherFun){
	var formModalId = formModalId == undefined ? '#formModal':'#'+formModalId;
	$(formModalId).on('hide.bs.modal', function (e) {
		var $target = $(e.target);
		//执行其他操作
		if(otherFun != undefined)
			otherFun();
		var $form = $target.find("form");
		$form.bootstrapValidator('resetForm', true);
		$form[0].reset();
		$form.find(":hidden").val('');
	});
}

/**bootstrap table显示序号
 * @param value 对应表格field的值
 * @param {Object} row 当前行的数据
 * @param {Number} index 行索引
 * */
function seqnumFormatter(value,row,index){
	return index+1;
}

/**operFormatterVUD中VUD表示table的操作栏目中显示view、update、del按钮
 * table显示 view、update、del按钮
 * @param value 对应表格field的值
 * @param {Object} row 当前行的数据
 * @param {Number} index 行索引
 * */
function operFormatterVUD(value,row,index){
	var viewBtn = "<button type='button' class='btn btn-default btn-xs' title='查看' onclick='view("+row.id+")'><span class='text-primary glyphicon glyphicon-eye-open'></span></button>";
	var modifyBtn = " <button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.id+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>";
	var delBtn = " <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.id+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
	return viewBtn + modifyBtn + delBtn;
}

/**table显示修改、删除按钮
 * @param value 对应表格field的值
 * @param {Object} row 当前行的数据
 * @param {Number} index 行索引
 * */
function operFormatter(value,row,index){
	return "<button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.id+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>" +
			" <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.id+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
}

/*========================面板操作============================*/

/**显示添加表单面板*/
BsFormTableExtend.prototype.showAddPanel = function(){
	$('#tablePanel').hide();
	$('#editPanel').show();
}

/**显示修改表单模态框。表单要加载的数据来自Bootstrap table插件。表单的模态框来自Bootstrap Modal插件。
 * @param {Number,required} id form要填充数据Id,必要参数
 * @param {String,required} action form表单要提交的地址
 * @param {String} datagridId form要填充数据所在的datagrid，默认值：dg
 * @param {String} formId 默认值ff
 * @param {String} editPanelId form所在模态框Id，默认值editPanel
 * @return 返回行记录数据
 * */
BsFormTableExtend.prototype.showModifyPanel = function(id, action, datagridId, formId, formPanelId){
	//设置datagrid、form、formModal的id
	var dg = datagridId == undefined ? 'dg':datagridId;
	var ff = formId == undefined ? 'ff':formId;
	var formPanelId = formPanelId == undefined ? 'editPanel':formPanelId;
	//根据Id获取对应行数据
	var data = $('#'+dg).bootstrapTable('getRowByUniqueId',id);
	$('#'+ff).attr('action',action);//设置form表单action
	$('#'+ff).autofill( data,{restrict:true} );//填充form表单

	$('#tablePanel').hide();
	$('#'+formPanelId).show();//显示编辑面板
	return data;
}

/**取消编辑，隐藏编辑模板,重置表单，显示列表
 * */
BsFormTableExtend.prototype.cancelEdit = function(){
	$('#tablePanel').show();
	var $editPanel = $('#editPanel');
	
	var $form = $editPanel.find('form');
	$form.bootstrapValidator('resetForm');
	$form[0].reset();
	$('input[type="file"]').val('');
	$form.find("input:hidden").val('');
	$editPanel.hide();
}

var g_bsFormTableExtend = new BsFormTableExtend();

$.fn.serializeObject = function(){    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};