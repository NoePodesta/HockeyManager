<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div id="addPlayerOverlay">

    <div class="title">Jugador</div>

    <p>Todos los campos con <span>*</span> deben ser completados.</p>
	
		<%String idTeam = request.getParameter("idTeam");%>
        <form id="myform" name="myform" method='post' action="PlayerManager?action=ADDPLAYER&idTeam=<%=idTeam%>" enctype="multipart/form-data">

            <div>
                <label>Nombre:<span>&#32;*&#32;:</span></label>
                <input id="playerName" name="playerName" type="text" required="required" minlength="3" value=""
                       placeholder="Nombre del Jugador"/></p>
            </div>
            <div>
                <label>Apellido:<span>&#32;*&#32;:</span></label>
                <input id="playerLastName" name="playerLastName" type="text" required="required" value=""
                       placeholder="Apellido del Jugador"/></p>
            </div>
            <div>
                <label>Email:</label>
                <input id="playerEmail" name="playerEmail" type="email" minlength="3" value=""
                       placeholder="Email del Jugador"/></p>
            </div>
            <div>
                <label>Posición del jugador:<span></span></label>
                <input id="playerPosition" name="playerPosition" type="text" value=""
                       placeholder="Posición del jugador"/></p>
            </div>
            <div><label>Nueva Imagen:&#32;</label>
            <input type="file" name="file" id="file" accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>
            </div>
            <output id="list"></output>

            <div class="pull-right">
                <button class="btn-primary btn-small" type="submit"> OK</button>
            </div>

        </form>
</div>

