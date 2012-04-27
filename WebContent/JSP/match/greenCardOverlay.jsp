<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Team"%>
<%@page import="model.Player"%>
<%@page import="model.GreenCard"%>

<div class="overview">
	<h1>Tarjetas Verdes</h1>
	
	<%String idmatch = (String) request.getAttribute("idmatch"); 
	System.out.println(idmatch);%>
	
	<form id="myform" method="post" action="MatchManager?idmatch=<%=idmatch%>">
	<input type="hidden" name="action" value="ADDGREENCARDS"/>
	
	<div class="opciones">		
		<table>
				<tr class="subtitulo"><%out.println(request.getAttribute("localname"));%></tr>
					<%List<GreenCard> greenCards = (List<GreenCard>) request.getAttribute("greenCards");
					List<Player> localplayers = (List<Player>) request.getAttribute("localplayers");
					System.out.println(localplayers);
					 	if (localplayers!=null) {
				 		
				 		for (int i = 0; i < localplayers.size(); i++){
				 			int quantity=0;
				 			String localname = localplayers.get(i).getName();
							String locallastname = localplayers.get(i).getLastName();
							int idteam = localplayers.get(i).getIdplayer();
							
							if (!greenCards.isEmpty()){
				 			for(int j=0; j<greenCards.size(); j++){
				 				
				 				if(greenCards.get(j).getPlayer().getIdplayer()==localplayers.get(i).getIdplayer()){
								
									quantity++;
				 		}}}%>
							
					<tr>
					<td><%=localname%> <%=locallastname%></td> 
					<td><input name="<%=localname+locallastname+idteam%>" type="number" required="required" value="<%=quantity%>"/></td>
					</tr>	
				<%}}else {%>
					<tr>
					<td> No se han ingresado jugadores todavia</td>
					</tr>
						<%}%>
						</table>
		</div>
	<div class="texto">
			<table>
			<tr class="subtitulo"><%out.println(request.getAttribute("guestname"));%></tr>
				<%List<Player> guestplayers = (List<Player>) request.getAttribute("guestplayers");
				System.out.println(guestplayers);
				 	if (guestplayers!=null) {
			 		
			 		for (int i = 0; i < guestplayers.size(); i++){
			 			int quantity=0;
			 			String guestname = guestplayers.get(i).getName();
						String guestlastname = guestplayers.get(i).getLastName();
						int idteam = guestplayers.get(i).getIdplayer();
						
						if (!greenCards.isEmpty()){
			 			for(int j=0; j<greenCards.size(); j++){
			 				
			 				if(greenCards.get(j).getPlayer().getIdplayer()==guestplayers.get(i).getIdplayer()){
							
								quantity++;
			 		}}}%>
						
				<tr>
				<td><%=guestname%> <%=guestlastname%></td> 
				<td><input name="<%=guestname+guestlastname+idteam%>" type="number" required="required" value="<%=quantity%>"/></td>
				</tr>	
			<%}}else {%>
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