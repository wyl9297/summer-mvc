<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/29
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>选择类型</title>
</head>
<body>
 <div>
     选择角色：
     <c:forEach items="${allUser}" var="userName">
            <input type="radio" value="${userName}" name="userRole"> ${userName}
     </c:forEach>
     <br>
     聊天类型
     <input type="radio" value="chatRoom" name="chatType"> 聊天室
     <input type="radio" value="oneToOne" name="chatType"> 一对一
     <br>
     <input type="button" value="go!" onclick="goChat()">
 </div>
</body>
<script type="text/javascript">
    function goChat() {
        var radio=document.getElementsByName("chatType");
        var chatType=null;   //  selectvalue为radio中选中的值
        for(var i=0;i<radio.length;i++){
            if(radio[i].checked==true) {
                chatType=radio[i].value;
                break;
            }
        }
        var radio1=document.getElementsByName("userRole");
        var userRole=null;   //  selectvalue为radio中选中的值
        for(var i=0;i<radio1.length;i++){
            if(radio1[i].checked==true) {
                userRole=radio1[i].value;
                break;
            }
        }
        window.open("goChat.html?chatType=" + chatType + "&userName=" + userRole,"_blank");
    }
</script>
</html>
