$(document).ready(function(){
	$("#hide").click(function(){
		$("#headimgdiv").css("display","block");
	})
	
	for(var i=1;i<41;i++){
		$("#headimgdl").append("<img class=\"changeimg\" id=\""+i+"\" src=\"res/img/headimg/"+i+"\.png\">");
	}
	
	$(".changeimg").click(function(){
		$.post("/dollars/changeimg.do",{id:this.id},function(data){
			alert(data);
			$("#headimgdiv").css("display","none");
		})
	})
	
	$(".x").click(function(){
		$("#headimgdiv").css("display","none");
	})
})