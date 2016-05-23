$("#submit").on('click',function(){
	var userName = $("input[name=userName]").val();
	var password = $("input[name=password]").val();
	if(userName == '' || password =='')
	{
		$("#errorMsg").html("用户名或密码不能为空");
		return false;
	}else{
		$.post("login/loginCheck",{"userName":userName,"password":password},function(data){
			if(data == 1){
				window.location.href="login/index";
			}else{
				$("#errorMsg").html("用户名或密码错误");
			}
		});
	}
});