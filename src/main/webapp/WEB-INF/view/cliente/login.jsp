<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form:form action="login" method="post" modelAttribute="formLogin">
            Email: <form:input path="email"/><br>
            Contrasena: <form:password path="contrasena"/><br>
            <form:button>Entrar</form:button>
        </form:form>
    </body>
</html>