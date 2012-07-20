<%@ page import="model.Player" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<div id="addPlayerOverlay">

    <%Player player = (Player)request.getAttribute("player");%>

    <div class="title">Modificar Datos</div>

    <p>Todos los campos con <span>*</span> deben ser completados.</p>

    <form id="myform" name="myform" method="POST" action="PlayerManager?action=MODIFYPLAYER&idPlayer=<%=player.getIdplayer()%>" enctype="multipart/form-data">

        <div>
            <label>Nombre:<span>&#32;*&#32;:</span></label>
            <input id="playerName" name="playerName" type="text" required="required" minlength="3" value="<%=player.getName()%>"
                   placeholder="Nombre del Jugador"/></p>
        </div>
        <div>
            <label>Apellido:<span>&#32;*&#32;:</span></label>
            <input id="playerLastName" name="playerLastName" type="text" required="required" value="<%=player.getLastName()%>"
                   placeholder="Apellido del Jugador"/></p>
        </div>
        <div>
            <label>Email:</label>
            <input id="playerEmail" name="playerEmail" type="email" minlength="3" value="<%=player.getEmail()%>"
                   placeholder="Email del Equipo"/></p>
        </div>
        <div>
            <label>Posición del jugador:<span></span></label>
            <input id="playerPosition" name="playerPosition" type="text" value="<%=player.getPosition()%>"
                   placeholder="Posición del jugador"/></p>
        </div>
        <div><label>Nueva Imagen:&#32;</label>
            <input type="file" name="file" id="file"
                   accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>
            <output id="list"></output>
        </div>
        <p>Si la imagen no se cambia, permanecerá la actual. </p>
        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>


    </form>

</div>

<script type="text/javascript">
    $(document).ready(function () {
        arg.fileStyle();

    });
</script>
