	<div class="titulo"> 
		<h1>Equipos</h1>
	</div>

	<%@page import="model.Fixture"%>
	<%@page import="model.Player"%>
	<%@page import="model.Team"%>
	<%@page import="java.util.List"%>


		
		<%
		List<Team> teams= (List<Team>)request.getAttribute("teams");
		Fixture fixture = (Fixture)request.getAttribute("fixture");
		String tournament = (String)session.getAttribute("tournament");
	
		if(!teams.isEmpty()){%>
			<div class="displayRight">
			<p><img src="image/modify.png" width="15" height="15" /> Modificar caracteristicas del equipo, <img src="image/addplayer.png" width="15" height="15"/> Agregue jugadores</p>
			</div>
			
		<%	for(int i = 0; i < teams.size(); i++){
				if(!teams.get(i).getName().equals("Libre")){
				String teamname = teams.get(i).getName();
				List<Player> players = teams.get(i).getPlayers();
				int idteam = teams.get(i).getIdTeam();
				
		%>
		
		<table>
		<tr>
		<td style="width: 75px; text-transform: capitalize"><div class="link"><a href="TeamManager?action=TEAMPROFILEOVERLAY&idteam=<%=idteam%>" rel="#overlay2" style="text-decoration: none"><%=teamname%></a></div></td>
		<td><a href="TeamManager?action=MODIFYTEAMSERVLET&idteam=<%=idteam%>" rel="#overlay6" style="text-decoration: none"><img src="image/modify.png" width="20px" height="20px"/></a></td>
		<td><%if(fixture==null){%><a href="TeamManager&action=DELETETEAM&idteam=<%=idteam%>"><img src="image/delete.png"/></a><%}%></td>
		<td><a href="JSP/player/addPlayerOverlay.jsp?idteam=<%=idteam%>" rel="#overlay5" style="text-decoration: none"><img src="image/addplayer.png" width="20px" height="20px"/></a></td>
		</tr>
		</table>
		
										
		<%}}%>
	
		<%}else{%> Usted no ha ingresado equipos todavia	

	
		<%}if(fixture==(null) && (tournamentName.equalsIgnoreCase(tournament))){
				%>
		
		
		
		<div class = "displayRight">
		<a href="TournamentManager?action=GENERATEFIXTURE" style="text-decoration: none"><button class="btn-primary btn-small" type="button">Generar Fixture</button></a>					
		<a href="addTeamOverlay.jsp" rel="#overlay2" style="text-decoration:none"><button class="btn-primary btn-small" type="button">Agregar Equipo</button></a>
		</div>
		
		
		<%} %>

		
		<div class="apple_overlay" id="overlay2">
			<div class="contentWrap"></div>
		</div>
			
		<div class="apple_overlay" id="overlay5">
			<div class="contentWrap"></div>
		</div>
		
		<div class="apple_overlay" id="overlay6">
			<div class="contentWrap"></div>
		</div>
 		
 		
			
		<script type="text/javascript">
			$(document).ready(function(){
				arg.linkDisplay();
																											
			});
		</script>

