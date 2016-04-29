/**@author 支煜
 * 2016-03
 * jquery confirm插件扩展
 */
function JqConfirmExtend(){
}

/**删除操作弹出框
 * @param {Number} index 要删除的行下标
 * @param {Function} func 点击确认按钮后要执行的操作
 * @param {String} title 弹出框标题
 * @param {String} content 弹出框内容
 * */
var g_icon={
	warning:'text-warning glyphicon glyphicon-warning-sign',
	danger:'text-danger glyphicon glyphicon-exclamation-sign'
};
JqConfirmExtend.prototype.delConfirm = function(index, func, title, content, icon){
	if(title == undefined || title == null)
		title = false;
	if(content == undefined || title == null)
		content = "确定删除第" + (index + 1) + "行么？";
	if(content == undefined || title == null)
		content = "确定删除第" + (index + 1) + "行么？";
	$.confirm({
		icon: g_icon[icon],
		title: title,
		content: content,
		confirmButton: '确定',
		cancelButton: '取消',
		confirmButtonClass: 'btn-danger',
		cancelButtonClass: 'btn-primary',
		confirm: func
	});
}

/**自动关闭弹框
 * @param {string} info 弹出框的提示信息
 * */
JqConfirmExtend.prototype.autoClose = function(info){
	$.confirm({
		title: false,
	    content: info == undefined ? '删除失败':info,
	    autoClose: 'confirm|3000',
	    cancelButton:false,
	    confirmButton: '关闭',
    	confirmButtonClass: 'btn-primary'
	});
}