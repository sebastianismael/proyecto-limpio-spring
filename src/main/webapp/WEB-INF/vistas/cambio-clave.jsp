<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<br/>
<form:form action="/sitio/cambiar-clave" method="POST" modelAttribute="datos">
    Usuario: <form:input path="email" id="email" type="email" class="form-control" /><br/>
    Clave Actual: <form:input path="claveActual" type="text" id="password" class="form-control"/><br/>
    Clave nueva: <form:input path="claveNueva" type="text" id="password" class="form-control"/><br/>
    Confirmar Clave nueva: <form:input path="repiteClaveNueva" type="text" id="repite-password" class="form-control"/><br/>
    <button Type="Submit"/>Registrarme</button>
</form:form>
<br/>
<br/>
<br/>
${error}
