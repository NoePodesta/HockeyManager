	<div class="titulo"> 
		<h1>Goles</h1>
	</div>

	<%@page import="java.util.List"%>
	<%@page import="model.Team"%>
	<%@page import="model.Player"%>
	
	
		<% 
		Team localteam = (Team)request.getAttribute("localteam");
		Team guestteam= (Team)request.getAttribute("guestteam");
		List<Player> localplayers = localteam.getPlayers();
		List<Player> guestplayers = localteam.getPlayers();
		int idmatch = (Integer) request.getAttribute("idmatch");%>		
		
		<form id="myform" method="post" action="MatchManager?idmatch=<%=idmatch%>">
		<input type="hidden" name="action" value="ADDGOALS"/>
		<div class="opciones">
		<table>
		<tr class="subtitulo"><%out.println(request.getAttribute("localname"));%></tr>
		<%for(int i = 0; i < localplayers.size(); i++){
			String localplayername = localplayers.get(i).getName();
			String localplayersurname = localplayers.get(i).getLastName();
			int idteam = localplayers.get(i).getIdplayer();
		%>	
		<tr>
			<td><%=localplayername%> <%=localplayersurname%></td> 
			<td><input name="<%=localplayername+localplayersurname+idteam%>" type="number" required="required" value="" /></td>
		</tr>	
		<%} %>
		</table>
		</div>
		<div class="texto">
		<table>
		<tr class="subtitulo"><%out.println(request.getAttribute("localname"));%></tr>
		<%for(int i = 0; i < guestplayers.size(); i++){
			String guestplayername = guestplayers.get(i).getName();
			String guestplayersurname = guestplayers.get(i).getLastName();
			int idteam = guestplayers.get(i).getIdplayer();
		%>	
		<tr>
			<td><%=guestplayername%> <%=guestplayersurname%></td> 
			<td><input name="<%=guestplayername+guestplayersurname+idteam%>" type="number" required="required" value="" /></td>
		</tr>	
		<%} %>
		</table>
		</div>
		
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>	
					
		</form>
