<%--
  Created by IntelliJ IDEA.
  User: kilogate
  Date: 2021/12/5
  Time: 00:28
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Select a file to upload</h1>
<form action="multipleUploadServlet" enctype="multipart/form-data" method="POST">
    Author: <input type="text" name="author"/><br/>
    Select first file to Upload <input type="file" name="filename"/><br/>
    Select second file to Upload <input type="file" name="filename"/><br/>
    <input type="submit" value="Upload">
</form>
</body>
</html>
