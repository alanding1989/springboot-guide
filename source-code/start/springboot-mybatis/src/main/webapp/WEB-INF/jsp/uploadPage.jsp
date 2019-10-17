<%--
  Created by IntelliJ IDEA.
  User: alanding
  Date: 2019/9/30
  Time: 下午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    选择图片:<input type="file" name="file" accept="image/*">
    <input type="submit" value="上传">
</form>
</body>
</html>
