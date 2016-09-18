/**公共脚本*/

/**获取上传文件的类型
 * @param file FileInput对象
 * @return int 字节
 * */
function getFileType(file){
	return file.files[0].type;
}

/**获取图片文件的宽、高、size
 * @param file FileInput对象
 * @param object 输出参数，必须的属性{'width','height','size'}
 * */
function getImgFile(file){
	var obj = {width:0,height:0,size:0};
	if(file.files){
		//HTML5方式
		//读取图片数据
		var f = file.files[0];
		var reader = new FileReader();
		reader.onload = function(e){
			var data = e.target.result;
			//加载图片获取图片真实宽度和高度
			var image = new Image();
			image.onload=function(){
				var width = image.width;
				var height = image.height;
				obj.width = width;
				obj.height = height;
				obj.size = f.size;
//				alert(width+'======'+height+"====="+f.size);
			};
			image.src= data;
		};
		reader.readAsDataURL(f);
	}else{
		var image = new Image(); 
		image.onload =function(){
			var width = image.width;
			var height = image.height;
			var fileSize = image.fileSize;
			obj.width = width;
			obj.height = height;
			obj.size = fileSize;
//			alert(width+'======'+height+"====="+fileSize);
		}
		image.src = file.value;
	}
	return obj;
}