var wsUri = "ws://localhost:8080/dollars/chatsocket.socket";

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
    var username = getCookie("username");
    var unique = getCookie("unique");
    websocket.send(username+"#"+unique+"#connect");
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
	var username = getCookie("username");
	var img = "<img src="+result[3]+" id=\"headimg\" />";
	if(nickname == username){
		$(".viewdiv").append("<dl class=\"me\">" +
							 "<dd>" +img+
							 "<p>"+nickname+"</p>" +
							 "</dd>" +
							 "<img src=\"res/img/chatbg/right.png\" />" +
							 "<dd id=\"content\">"+message+"</dd>" +
							 "</dl>");
	}else{
		$(".viewdiv").append("<dl>" +
							"<dd>" +img+
							"<p>"+nickname+"</p>" +
							"</dd>" +
							"<img src=\"res/img/chatbg/left.png\" />" +
							"<dd id=\"content\">"+message+"</dd>" +
							"</dl>");
	}
	
	$("<audio autoplay=\"autoplay\">" +
	   "<source src=\"res/sound/ding.wav\" type=\"audio/wav\">" + 
	   "</audio>").appendTo('body');
	
	document.getElementById('div').scrollTop=document.getElementById('div').scrollHeight;
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