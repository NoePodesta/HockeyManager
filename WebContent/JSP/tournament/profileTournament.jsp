<%@page import="model.Tournament"%>
<%@page import="model.Fixture"%>
<%@page import="enums.Privilege"%>
<%@page import="model.Team"%>
<%@page import="java.util.List"%>

<%
	String privilege1 = (String) session.getAttribute("privilege");
	Tournament tournament = (Tournament) request.getAttribute("tournament");
	List<Team> teams = (List<Team>) request.getAttribute("teams");
	String yourTournament = (String)request.getAttribute("yourTournament");
	Fixture fixture = tournament.getFixture();
	System.out.println(yourTournament);
%>

<div id="tournamentProfile">
	<h1><%=tournament.getName()%></h1>
	<%if((privilege1!=null && privilege1.equalsIgnoreCase(Privilege.ADMINISTRADOR
			
			.getValue())) || yourTournament=="true"){ %>
	<div class = "pull-right">
	 	<a href="TournamentManager?action=MODIFYTOURNAMENTPAGE" rel="#overlay"><img src="image/modify.png" width="20" height="20" /></a>
		<%if(fixture==(null)){%>
        <a href="JSP/team/addTeamOverlay.jsp" rel="#overlay"><img src="image/addTeam.png" width="20" height="20" /></a>
            <%if(teams.size()>=2){ %>
        <a href="TournamentManager?action=GENERATEFIXTURE" style="text-decoration: none"><button class="btn-primary btn-small" type="button">Generar Fixture</button></a>
		<%}} %>
	</div>
	
	<%} %>
	
	<div class="tournamentSummary">
		<div class="img">
			<img class="userImg" src=ImageController?action=TOURNAMENT&value=<%=tournament.getIdTournament()%> alt="Tournament Image" />
		</div>
		<div class="summary">
			<div>
				<%=tournament.getDescription()%>
			</div>
		</div>
	</div>
	<%if(!teams.isEmpty()){
		for(Team team: teams){ %>
	<ul id="holder">
		<li class="tournamentSummary">
			<div class="img">
				<img class="userImg" src=ImageController?action=TEAM&value=<%=team.getIdTeam()%> alt="Team Image" />
			</div>
			<div class="summary">
				<div class="title">
                    <a href="TeamManager?action=TEAMPROFILEOVERLAY&value=<%=team.getIdTeam()%>"><b><%=team.getName()%></b></a>
				</div>
				<div>
					<%=team.getDescription()%>
				</div>
			</div>
		</li>
	</ul>
	<%}} else{%>
		
		No Se han ingresado Equipos!
		
	<%}%>
</div>