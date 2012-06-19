<%@page import="model.Team"%>
<%@page import="java.util.List"%>
				<table id="fixtureview" class="site_center4">
					<tr>
						<th>EQUIPO</th>
						<th>PUNTOS</th>
						<th>PJ</th>
						<th>PG</th>
						<th>PE</th>
						<th>PP</th>
						<th>GF</th>
						<th>GC</th>
						<th>DIF</th>
					</tr>
					<%List<Team> teams = (List<Team>) request.getAttribute("teams");

 				for (Team team: teams) {

			 		String teamname = team.getName();
			 		int teampts = team.getPts();
			 		int teampj = team.getPj();
			 		int teampg = team.getPg();
			 		int teampp = team.getPp();
			 		int teampe = team.getPe();
			 		int teamdif = team.getDiferencia();
			 		int teamgc = team.getGc();
			 		int teamgf = team.getGf();
			 		
			 			
			 		 %>
					<tr>
					
						<td><label><a href="profileTeamII?teamname=<%=teamname%>" rel="#overlay"><%=teamname%></a></label></td>
						<td><label><%=teampts%></label></td>
						<td><label><%=teampj%></label></td>
						<td><label><%=teampg%></label></td>
						<td><label><%=teampe%></label></td>
						<td><label><%=teampp%></label></td>
						<td><label><%=teamgf%></label></td>
						<td><label><%=teamgc%></label></td>
						<td><label><%=teamdif%></label></td>
					</tr>
					
					<%}%>
					</table>
				PJ= partidos jugados, PG= Partidos ganados, PE= Partidos
				empatados, PP= Partidos perdidos, GF= Goles a favor, GC= Goles en contra, 
				DIF= Diferencia entre goles en contra y a favor.
			