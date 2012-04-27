<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Team"%>
<%@page import="model.Player"%>
<%@page import="model.YellowCard"%>

<div class="overview">
	<h1>Tarjetas Amarillas</h1>
	
	<%int idmatch = (Integer) request.getAttribute("idmatch"); %>
	
	<form id="myform" method="post" action="MatchManager?idmatch=<%=idmatch%>">
	<input type="hidden" name="action" value="ADDYELLOWCARDS"/>
	
	<div class="opciones">
		<table>
			<tr class="subtitulo"><%out.println(request.getAttribute("localname"));%></tr>
				<%List<Player> localplayers = (List<Player>) request.getAttribute("localplayers");
				List<YellowCard> yellowCards = (List<YellowCard>) request.getAttribute("greenCards");
			 	if (!localplayers.isEmpty()) {
			 		int quantity=0;
			 		for (int i = 0; i < localplayers.size(); i++){
			 			String localname = localplayers.get(i).getName();
						String locallastname = localplayers.get(i).getLastName();
						int idplayer = localplayers.get(i).getIdplayer();
						
						if (!yellowCards.isEmpty()){
			 			for(int j=0; j<yellowCards.size(); j++){
			 				
			 				if(yellowCards.get(j).getPlayer().getIdplayer()==localplayers.get(i).getIdplayer()){
							
								quantity++;
			 		}}%>
						
				<tr>
				<td><%=localname%> <%=locallastname%></td> 
				<td><input name="<%=localname+locallastname+idplayer%>" type="number" required="required" value=<%=quantity%>/></td>
				</tr>	
			<%}}}else {%>
				<tr>
				<td> Usted no ha ingresado jugadores todavia</td>
				</tr>
					<%}%>
		</table>
		</div>
	<div class="texto">
			<table>
			<tr class="subtitulo"><%out.println(request.getAttribute("localname"));%></tr>
				<%List<Player> guestplayers = (List<Player>) request.getAttribute("guestplayers");
				 	if (!guestplayers.isEmpty()) {
			 		int quantity=0;
			 		for (int i = 0; i < localplayers.size(); i++){
			 			String guestname = guestplayers.get(i).getName();
						String guestlastname = guestplayers.get(i).getLastName();
						int idteam = guestplayers.get(i).getIdplayer();
						
						if (!yellowCards.isEmpty()){
			 			for(int j=0; j<yellowCards.size(); j++){
			 				
			 				if(yellowCards.get(j).getPlayer().getIdplayer()==guestplayers.get(i).getIdplayer()){
							
								quantity++;
			 		}}%>
						
				<tr>
				<td><%=guestname%> <%=guestlastname%></td> 
				<td><input name="<%=guestname+guestlastname+idteam%>" type="number" required="required" value=<%=quantity%>/></td>
				</tr>	
			<%}}}else {%>
				<tr>
				<td> No se han ingresado jugadores todavia</td>
				</tr>
					<%}%>
		</table>
		</div>
		
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>	
					
		</form>
</div>