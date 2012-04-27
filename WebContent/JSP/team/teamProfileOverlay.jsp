<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div class="profile">
	
	<%@page import="model.Player"%>
	<%@page import="DAO.GoalDao"%>  
	<%@page import="DAO.GreenCardDao"%>  
	<%@page import="java.util.List"%>
	
	<%String idteam = (String)request.getAttribute("idteam");%>
	<h1><%out.println(request.getAttribute("teamname"));%></h1>
	<p><label>Descripción del equipo: </label><%out.println(request.getAttribute("description"));%></p>
	
	<p><label>Historia del equipo: </label><%out.println(request.getAttribute("history"));%></p>
	<p><label>Jugadores:  </label><%
 	List<Player> players = (List<Player>) request.getAttribute("players");
 	if (!players.isEmpty()) {
 		for (int i = 0; i < players.size(); i++) {
 			String playerid = String.valueOf(players.get(i).getIdplayer());
 			int goalq = GoalDao.getGoal(playerid).size();
 			int green = players.get(i).getGreenCards().size();
 			System.out.println(green); 
 			String name = players.get(i).getName();
 			String lastname = players.get(i).getLastName();
 						
			
 %>
				<ul>
					<li>
						<%=name + " " + lastname%><a href="PlayerManager?action=DELETEPLAYER&idteam=<%=idteam%>&nameplayer=<%=name%>&lastnameplayer=<%=lastname%>"><img src="image/delete.png"/></a><img src="image/goles.png" width="20" height="20"/>(<%=goalq%>) <img src="image/tarjeta_verde.png" width="10" height="10"/>(<%=green%>)
					</li>
				</ul>
				<%
					}
					} else {%>
						Usted no ha ingresado jugadores todavia
					<%}%>

	
	

	</div>

</div>