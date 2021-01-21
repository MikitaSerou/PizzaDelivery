<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>

<form method="POST" action="/login">
    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" placeholder="Username" autofocus="true"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" laceholder="Password"></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="remember-me"></td>
            <td>Remember me on this Computer</td>
        </tr>
        <tr>
            <td><button type="submit">Log in</button></td>
        </tr>
    </table>
    <h4><a href="/registration">Зарегистрироваться</a></h4>
</form>
<%--<div>--%>
<%--    <form method="POST" action="/login">--%>
<%--        <h2>Вход в систему</h2>--%>
<%--        <div>--%>
<%--            <input name="username" type="text" placeholder="Username"--%>
<%--                   autofocus="true"/>--%>
<%--            <input name="password" type="password" placeholder="Password"/>--%>
<%--            <button type="submit">Log In</button>--%>
<%--            <h4><a href="/registration">Зарегистрироваться</a></h4>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>

</body>
</html>