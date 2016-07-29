/**@author 支煜
 * 2016-03
 * 树形控件扩展功能
 */
function TreeUtil(){
}

//邻接数据转换为嵌套树
TreeUtil.prototype.adjTransToNest = function(data){
	//将数据转换为treegrid支持的数据
	var ary = new Array();
	ary.push(data[0]);	
	for(var i=1; i<data.length; i++){
		var item = data[i];
		//获取被添加节点的父节点
		var currentItem = ary[ary.length-1];
		for(var j=2; j<item.hierarchy; j++){
			var subAry = currentItem.children;
			currentItem = subAry[subAry.length-1];
		}
		//将当前循环节点放入父节点
		if(currentItem.children == null)	//当前无子节点则创建子节点数组
			currentItem.children = new Array();
		currentItem.children.push(item);
	}
	return ary;
}