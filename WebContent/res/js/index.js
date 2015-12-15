$(document).ready(function(){
	$("#hide").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(username != "" && password != ""){
			$(".code").css("display","block");
		}
	})
	
	$(".x").click(function(){
		$(".code").css("display","none");
	})
	
	$("#button").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		var accesscode = $("#accesscode").val();
		$.post("register.do",
			{username:username,password:password,repassword:repassword,accesscode:accesscode},
			function(data){
				alert(data);
				$(".code").css("display","none");
			})
	})
})