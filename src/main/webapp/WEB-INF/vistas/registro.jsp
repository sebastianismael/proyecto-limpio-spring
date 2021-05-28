<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
Hola, ${mostrar}!
<br/>
<br/>
<br/>
<form:form action="/sitio/registrar" method="POST" modelAttribute="registro">
    Usuario: <form:input path="email" id="email" type="email" class="form-control" /><br/>
    Clave: <form:input path="password" type="text" id="password" class="form-control"/><br/>
    Confirmar Clave: <form:input path="repitePassword" type="text" id="repite-password" class="form-control"/><br/>
    <button Type="Submit"/>Registrarme</button>
</form:form>
<br/>
<br/>
<br/>
${error}
