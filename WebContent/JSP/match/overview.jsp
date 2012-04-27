<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Team"%>
<%@page import="model.Player"%>
<%@page import="model.Goal"%>

<div class="overview">
	<h1>Resumen</h1>
	
	<div class="opciones">
	<table>
	<tr class="subtitulo"><%out.println(request.getAttribute("localname"));%></tr>
	<%List<Player> localplayers = (List<Player>) request.getAttribute("localplayers");
	List<Goal> goals = (List<Goal>) request.getAttribute("goals");
 	if (!localplayers.isEmpty()) {
 		for (int i = 0; i < localplayers.size(); i++){
 			String name = localplayers.get(i).getName();
			String lastname = localplayers.get(i).getLastName();
			int quantity=0;
 			for(int j=0; j<goals.size(); j++){
 				
 				if(goals.get(j).getPlayer().getIdplayer()==localplayers.get(i).getIdplayer()){
				
					quantity++;
 				}}%>
				<tr>	
				<td><%=name + " " + lastname%></th>
				<td><img src="image/goles.png" width="20" height="20"/><%=quantity %></td>
				</tr>			<%
					}}else {%>
						<p>Usted no ha ingresado jugadores todavia</p>
					<%}%>
	</table>
	</div>

	<div class="texto">
	<table>
	<tr class="subtitulo"><%out.println(request.getAttribute("guestname"));%></tr>
	<%List<Player> guestplayers = (List<Player>) request.getAttribute("guestplayers");
 	if (!localplayers.isEmpty()) {
 		for (int i = 0; i < guestplayers.size(); i++){
 			String name = guestplayers.get(i).getName();
			String lastname = guestplayers.get(i).getLastName();
			int quantity=0;
			for(int j=0; j<goals.size(); j++){
 				
 				if(goals.get(j).getPlayer().getIdplayer()==guestplayers.get(i).getIdplayer()){
				
					quantity++;
 				}}%>
				<tr>	
				<td><%=name + " " + lastname%></th>
				<td><img src="image/goles.png" width="20" height="20"/><%=quantity %></th>
				</tr>
				<%}}else {%>
						<p>Usted no ha ingresado jugadores todavia</p>
					<%}%>

	
	
	</table>
	</div>
</div>