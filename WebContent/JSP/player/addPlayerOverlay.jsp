<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div class="addPlayer">
	
	<h1>Jugadora</h1>
	
		<%String idteam = (String)request.getParameter("idteam");%>	
		<form id="myform" method="post" action="PlayerManager?idteam=<%=idteam%>">
		<input type="hidden" name="action" value="ADDPLAYER"/>
					
			<p><label>Nombre <span>*</span></label> 
			<input type="text" title="Ingrese el nombre del jugador"
					required="required" name="name"/></p>
								
			<p><label>Apellido <span>*</span></label> 
			<input type="text" title="Ingrese el apellido del jugador"
					required="required" name="lastname"/></p>
			<p><label>E-mail </label> 
			<input type="email" title="Ingrese el e-mail del jugador"
					name="email"/></p>
			<p><label>Posición </label> 
			<input type="text" title="Posición en la que juega el juegador"
					name="position" /></p>
			<p><label>Foto</label> 
			<input type="file" lang="es" name="photo" id="photo" style="margin-top: 5px;" /></p>
			
		
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>	
					
		</form>
		
	</div>