<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
//加工类型
var g_processType = ${processTypeMap};
//行业分类
var g_trade = {"1":"服装","2":"服饰","3":"家纺","4":"面料"};
//省、市 编码和名称键值对
var g_district = ${districtMap}

/**获取加工类型名称
 * @param processTypeStr 逗号分隔的字符串
 * @return 有可能返回空串
 */
function comm_getProcessTypeName(processTypeStr){
	var ary = processTypeStr.split(',');
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

/**获取省、市地区名称
 * @param districtStr 逗号分隔的地区编码字符串
 * @param sep 名称间的分隔符
 * @return 有可能返回空串
 */
function comm_getDistrictName(districtStr, sep){
	var ary = districtStr.split(',');
	if(sep == undefined || sep == null)
		sep = '';
	var str = null;
	if(ary.length>0)
		str = g_district[ary[0]];
	else
		return '';
	for(var i=1;i<ary.length;i++)
		str += sep+g_district[ary[i]];
	return str;
}
</script>