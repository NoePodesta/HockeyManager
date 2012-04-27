<%@page import="java.util.List"%>
<%@page import="model.Team"%>
				<table cellpadding="5" id="fixtureview" class="site_center4">
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

 				for (int i = 0; i < teams.size(); i++) {

			 		String teamname = teams.get(i).getName();
			 		int teampts = teams.get(i).getPts();
			 		int teampj = teams.get(i).getPj();
			 		int teampg = teams.get(i).getPg();
			 		int teampp = teams.get(i).getPp();
			 		int teampe = teams.get(i).getPe();
			 		int teamdif = teams.get(i).getDiferencia();
			 		int teamgc = teams.get(i).getGc();
			 		int teamgf = teams.get(i).getGf();
			 		
			 			
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
			