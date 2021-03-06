<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
//加工类型
var g_processType = ${processTypeMap};
//行业分类
var g_trade = {"1":"服装","2":"服饰","3":"家纺","4":"面料"};

/**获取加工类型名称
 * @param processTypeStr 逗号分隔的字符串
 * @return 有可能返回空串
 */
function comm_getProcessTypeName(processTypeStr){
	processTypeStr = processTypeStr.toString();//传入的有可能是整数类型，需转换为string
	var ary = processTypeStr.split(',');
	//传入的为''
	if(ary[0].length==0)
		return '';
	
	var str = null;
	if(ary.length>0)
		str = g_processType[ary[0]];
	else
		return '';
	for(var i=1;i<ary.length;i++)
		str += ' '+g_processType[ary[i]];
	return str;
}

/**获取行业名称
 * @param tradeStr 逗号分隔的字符串
 * @return 有可能返回空串
 */
function comm_getTradeName(tradeStr){
	var ary = tradeStr.split(',');
	var str = null;
	if(ary.length>0)
		str = g_trade[ary[0]];
	else
		return '';
	for(var i=1;i<ary.length;i++)
		str += ','+g_trade[ary[i]];
	return str;
}
</script>