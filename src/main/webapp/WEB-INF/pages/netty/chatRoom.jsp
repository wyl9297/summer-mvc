<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>聊天室</title>
</head>
<body>
<script type="text/javascript">
    var user = '<%=request.getParameter("userName")%>';
    if( user == "" || user == 'null' || user == undefined){
        user = "游客";
    }
    var socket;
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }
    if (window.WebSocket) {
        socket = new WebSocket("ws://localhost:9999/websocket");
        socket.onmessage = function(event) {
            var ta = document.getElementById('responseText');
            ta.value = ta.value + '\n' + event.data
        };
        socket.onopen = function(event) {
            var ta = document.getElementById('responseText');
            ta.value = "连接开启!";
            send("$system#" + user);
        };
        socket.onclose = function(event) {
            var ta = document.getElementById('responseText');
            ta.value = ta.value + "连接被关闭";
        };
    } else {
        alert("你的浏览器不支持 WebSocket！");
    }
    function send(message) {
        if (!window.WebSocket) {
            return;
        }
        if( message == undefined || message == null || message == "" ){
            alert("消息不能为空！");
            return;
        }
        if (socket.readyState == WebSocket.OPEN) {
            socket.send(message);
            document.getElementById("message").value="";
        } else {
            alert("连接没有开启.");
        }
    }
</script>
<center>
    <form onsubmit="return false;">
        <h3>聊天室：</h3>
        <textarea id="responseText" style="width: 500px; height: 300px;background-color: white;" disabled="disabled"></textarea>
        <br> <input type="text" id="message"  name="message" style="width: 300px"
                    value=""> <input type="button" value="发送消息" onclick="send(this.form.message.value)"> <input
            type="button"
            onclick="javascript:document.getElementById('responseText').value=''"
            value="清空聊天记录">
    </form>
</center>
<br>
<br>
</body>
</html>
