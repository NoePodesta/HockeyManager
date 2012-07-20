<%@ page import="model.Team" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div id="addTeamOverlay">
	
	<%Team team = (Team)request.getAttribute("team");%>

    <div class="title">Modificar&#32;<%=team.getName()%></div>
	
	<p>Todos los campos con <span>*</span> deben ser completados.</p>

        <form id="myform" name="myform" method='post' action="TeamManager?action=MODIFYTEAM&idTeam=<%=team.getIdTeam()%>" enctype="multipart/form-data">

            <div class="counter">
                <label>Descripción del Equipo<span>&#32;*&#32;:</span></label>
                <textarea name="description" id="description" cols="50" rows="4" required="required"><%=team.getDescription()%></textarea></p>
            </div>
            <div style="margin-bottom: 1em; text-align: left"><label>Imagen Actual:&#32;</label>
                <img style="margin-left: 4em" class="image" src=ImageController?action=TEAM&value=<%=team.getIdTeam()%>&size=80
                     alt="Tournament Image"/>
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
		
		<script type="text/javascript">
						$(document).ready(function(){
							arg.countCharacters();												
						});
		</script>
</div>
