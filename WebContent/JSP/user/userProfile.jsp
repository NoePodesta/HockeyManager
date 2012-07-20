<%@ page import="model.Team" %>
<%@ page import="model.User" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div id="modifyProfile">

    <%User user = (User)request.getAttribute("user");%>

    <div class="title">Modificar datos de &#32;<%=user.getName()%></div>

    <p>Todos los campos con <span>*</span> deben ser completados.</p>

    <form id="myform" name="myform" method='post' action="UserManager?action=MODIFYUSER" enctype="multipart/form-data">

        <div>
            <label>Nombre:<span>&#32;*&#32;:</span></label>
            <input id="name" name="name" type="text" required="required" minlength="3" value="<%=user.getName()%>"
                   placeholder="Nombre"/></p>
        </div>
        <div>
            <label>Apellido:<span>&#32;*&#32;:</span></label>
            <input id="lastName" name="lastName" required="required" type="text" required="required" value="<%=user.getLastName()%>"
                   placeholder="Apellido"/></p>
        </div>
        <div>
            <label>Email:</label>
            <input id="email" name="email" required="required" type="email" minlength="3" value="<%=user.getEmail()%>"
                   placeholder="Email del Equipo"/></p>
        </div>
        <div>
            <label>Contraseña:</label>
            <input id="p1" name="newPassword" required="required" type="password" minlength="3"
                   placeholder="Contraseña"/></p>
        </div>
        <div><label>Nueva Imagen:&#32;</label>
            <input type="file" name="file" id="file"
                   accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>
        </div>
        <p>Si la imagen no se cambia, permanecerá la actual. </p>
        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>
    </form>
    <form action="UserManager" method="post">
        <input type="hidden" name="action" value="DELETEUSER" />
        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> Borrar Usuario</button>
        </div>
    </form>


</div>
