<%--
  Created by IntelliJ IDEA.
  User: kilogate
  Date: 2021/12/5
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asynchronous Servlet</title>
</head>
<body>
Main thread: <%=request.getAttribute("mainThread")%><br/>
Work thread: <%=request.getAttribute("workThread")%><br/>
</body>
</html>
