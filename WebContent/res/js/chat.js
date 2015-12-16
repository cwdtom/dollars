var wsUri = "ws://183.129.153.210/dollars/chatsocket.socket";
//var wsUri = "ws://localhost:8080/dollars/chatsocket.socket";

function send_message() {
	 
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) {
        onOpen(evt)
    };
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };

}

jQuery.fn.extend({
	enter: function(fn){
		$(this).bind('keydown',function(event){var e = event || window.event;if(!e.ctrlKey && e.keyCode ==13){if(typeof(fn)!='undefined')fn.call(this)}});
		return this;
	}
});

function onOpen(evt) {
    console.log("Connected to Endpoint!");
    var unique = getCookie("unique");
    
    $.post("/dollars/getusername.do",{unique: unique},function(data){
    	var username = data;
    	websocket.send(username+"#"+unique+"#connect");
    })
}

function onMessage(evt) {
	console.log("Message Received: " + evt.data);
	var result = evt.data.split("#");
	var nickname = result[0];
	var message = result[1];
	if(result[2] == "join"){
		message = message + "加入了聊天室！";
	}
	if(result[2] == "close"){
		message = message + "退出了聊天室！";
	}
	var unique = getCookie("unique");
	$.post("/dollars/getusername.do",{unique: unique},function(data){
		var username = data;
		if(nickname == "SERVER"){
			result[3] = "res/img/headimg/-1.png";
		}
		var img = "<img src="+result[3]+" id=\"headimg\" />";
		
		var array = result[3].split("");
		if(array[17] == "."){
			var id = array[16];
		}else if(array[16] == "-"){
			var id = -1;
		}else{
			var id = array[17];
		}
		
		$(".postbutton").css("background-image","url(res/img/chatbg/"+id+".png)");
		
		if(nickname+"\r\n" == username){
			$(".viewdiv").append("<dl class=\"me\">" +
								 "<dd>" +img+
								 "<p>"+nickname+"</p>" +
								 "</dd>" +
								 "<img src=\"res/img/chatbg/right.png\" />" +
								 "<dd id=\"content\" style=\"background-image: url(res/img/chatbg/"+id+".png);\">"+message+"</dd>" +
								 "</dl>");
		}else{
			$(".viewdiv").append("<dl>" +
								"<dd>" +img+
								"<p>"+nickname+"</p>" +
								"</dd>" +
								"<img src=\"res/img/chatbg/left.png\" />" +
								"<dd id=\"content\" style=\"background-image: url(res/img/chatbg/"+id+".png);\">"+message+"</dd>" +
								"</dl>");
		}
		
		$("<audio autoplay=\"autoplay\">" +
		   "<source src=\"res/sound/ding.wav\" type=\"audio/wav\">" + 
		   "</audio>").appendTo('body');
		
		document.getElementById('div').scrollTop=document.getElementById('div').scrollHeight;
	})
}

function onError(evt) {
	console.log('ERROR:' + evt.data);
}

function doSend(message) {
	console.log("Message Sent: " + message);
    websocket.send(message);
}

send_message();

$(document).ready(function(){
	$(".postbutton").click(function(){
		var message = $("#posttextarea").val();
		doSend(message);
		$("#posttextarea").val("");
	})
	
	$("#posttextarea").enter(function(){
		var message = $("#posttextarea").val();
		doSend(message);
		$("#posttextarea").val("");
	})
})

function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }
    else{
        return null;
    }
}