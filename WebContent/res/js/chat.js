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

function onOpen(evt) {
    console.log("Connected to Endpoint!");
}

function onMessage(evt) {
	console.log("Message Received: " + evt.data);
	var result = evt.data.split("#");
	var nickname = result[0];
	var message = result[1];
	if(result[2] == "join"){
		message = message + "加入了聊天室！";
	}
	$(".viewdiv").append("<dl>" +
						"<dd>" +
						"<img src=\"res/img/headimg/1.png\" id=\"headimg\" \/>" +
						"<p>"+nickname+"</p>" +
						"</dd>" +
						"<img src=\"res/img/chatbg/left.png\" />" +
						"<dd id=\"content\">"+message+"</dd>" +
						"</dl>");
	
	$("<audio id=\"chatAudio\">" +
	   "<source src=\"notify.mp3\" type=\"audio/mpeg\">" + 
	   "</audio>").appendTo('body');
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
})