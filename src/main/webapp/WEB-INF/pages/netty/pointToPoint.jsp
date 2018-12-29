<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>一对一聊天</title>
    <script type="text/javascript">
        var user = '<%=request.getParameter("userName")%>';
        if( user == "" || user == 'null' || user == undefined){
            user = "游客";
        }
        var socket;
        if(!window.WebSocket){
            window.WebSocket = window.MozWebSocket;
        }
        if(window.WebSocket){
            socket = new WebSocket("ws://localhost:8899/websocket");
            socket.onmessage = function(event){
                var ta = document.getElementById('responseContent');
                ta.value += event.data + "\r\n";
            };

            socket.onopen = function(event){
                var ta = document.getElementById('responseContent');
                ta.value = "你当前的浏览器支持WebSocket,请进行后续操作\r\n";
                send("$system#" + user);
            };

            socket.onclose = function(event){
                var ta = document.getElementById('responseContent');
                ta.value = "";
                //ta.value = "WebSocket连接已经关闭\r\n";
                ta.value = "当前账号已被下线，请更换账号重新登录\r\n";
            };
        }else{
            alert("您的浏览器不支持WebSocket");
        }


        function send(message){
            if(!window.WebSocket){
                return;
            }
            if(socket.readyState == WebSocket.OPEN){
                socket.send(message);
            }else{
                alert("WebSocket连接没有建立成功！！");
            }
        }
    </script>
</head>
<body>
<form onSubmit="return false;">
    当前账号：
    <input type = "text" name = "senderId" placeholder="当前账号" value="<%=request.getParameter("userName")%>" disabled="disabled" style="background-color: white"/>
    <br/><br/>
    <%--<input type = "text" name = "receiverId" placeholder="接收者唯一id"/>--%>
    请选择接收人：
    <c:forEach items="${allUser}" var="user">
        <input type="radio" value="${user}" name="receiverId"> ${user}
    </c:forEach>
    <br/><br/>
    <input type = "text" name = "message" placeholder="消息内容"/>
    <br/><br/>
    <input type = "button" value = "发送WebSocket请求消息" onClick = "send(this.form.senderId.value+','+this.form.receiverId.value+','+this.form.message.value)"/>
    <hr color="red"/>
    <h2>客户端接收到服务端返回的应答消息</h2>
    <textarea id = "responseContent" style = "width:1024px; height:300px;background-color: white" disabled="disabled"></textarea>
</form>
</body>
</html>
