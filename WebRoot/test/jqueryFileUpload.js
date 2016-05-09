$(function () {
	//通过以下脚本对JQuery File Upload插件进行初始化，完成插件功能与显示效果
    $('#fileupload').fileupload({
        dataType: 'json',	//从服务器返回的数据类型
        done: function (e, data) {	//上传请求成功完成后的回调处理方法
            $.each(data.result.files, function (index, file) {
                $('<p/>').text(file.name).appendTo('#files');
            });
        },
        progressall:function(e,data){	//回调函数progressall处理进度条控件
        	var progress = parseInt(data.loaded/data.total * 100, 10);
        	$('#progress.progress-bar').css('width',progress+'%');
        }
    });
});