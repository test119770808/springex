<%--
  Created by IntelliJ IDEA.
  User: sc_tjoeun
  Date: 2024-04-19
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register.jsp</title>
</head>
<body>
    <form action="/todo/register" method="post">
        <div>
            Title : <input type="text" name="title">
        </div>
        <div>
            DueDate : <input type="date" name="dueDate">
        </div>
        <div>
            Writer : <input type="text" name="writer">
        </div>
        <div>
            Finished : <input type="checkbox" name="finished">
        </div>
        <div>
            <button type="submit">Register</button>
        </div>
    </form>
</body>
</html>
