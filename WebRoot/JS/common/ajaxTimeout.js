/** 
 * 设置未来(全局)的AJAX请求默认选项，主要设置了AJAX请求遇到Session过期的情况 
 */  
$.ajaxSetup({  
    type: 'POST',
	//请求完成后回调函数 (请求成功或失败之后均调用)。
    //xhr：XMLHttpRequest 对象，status：描述成功请求类型的字符串，值为success或error。
    complete: function(xhr,status)
    {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus == 'timeout')
        {
        	var top = getTopWinow();
            if (confirm('由于您长时间没有操作, 会话已过期, 请重新登录.'))
                top.location.href = xhr.getResponseHeader('redirect');
        }
	}
});
  
/** 
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * @return 当前页面的顶层窗口对象 
 */  
function getTopWinow(){
    var p = window;
    while(p != p.parent)
        p = p.parent;
    return p;
}