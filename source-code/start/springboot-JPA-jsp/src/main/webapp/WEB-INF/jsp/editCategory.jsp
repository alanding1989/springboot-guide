<%--
  Created by IntelliJ IDEA.
  User: alanding
  Date: 2019/9/30
  Time: 上午8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<div style="margin:0px auto; width:500px">
    <form action="updateCategory" method="post">
        name: <input name="name" value="${c.name}"><br>
        <input name="id" type="hidden" value="${c.id}">
        <button type="submit">提交</button>
    </form>
</div>
