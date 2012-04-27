<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div class="modifyTeamOverlay">
	
	<%String idteam = (String)request.getAttribute("idteam");%>
	
	<div class="titulo">
	<h1>${teamname }</h1>
	</div>
	
	<p>Todos los campos con <span>*</span> deben ser completados.</p>
	
	 	<form id="myform" name="myform" method='post' action="TeamManager?idteam=<%=idteam%>">
			<input type="hidden" name="action" value="MODIFYTEAM"/>	
			
			
			<p><label>Descripción del Equipo <span>*</span></label> 
			<div class="counter">
			<textarea name="description" id="description" cols= "50" rows= "4" required="required">${description }</textarea>
			</div></p>
			
			
			<p><label>Historia del Equipo <span>*</span></label> 
			<div class="counter">
			<textarea name="history" id="description" cols= "50" rows= "4" required="required">${history }</textarea>
			</div></p>		
			
			<p><label>Agregar Imagen<br/> <input type="file" name="photo" style="margin-top: 5px;" />
			</label><br/></p>
					
	
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>
		
		</form>
		
		<script type="text/javascript">
						$(document).ready(function(){
							arg.countCharacters();												
						});
		</script>
</div>