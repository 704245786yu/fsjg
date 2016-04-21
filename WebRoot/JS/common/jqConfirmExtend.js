/**@author 支煜
 * 2016-03
 * jquery confirm插件扩展
 */
function JqConfirmExtend(){
}

/**删除操作弹出框
 * @param {Number} index 要删除的行下标
 * @param {Function} func 点击确认按钮后要执行的操作
 * */
JqConfirmExtend.prototype.delConfirm = function(index, func){
	$.confirm({
		title: false,
		content: "确定删除第" + (index + 1) + "行么？",
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