<%@page import="model.Tournament"%>
<%@page import="model.Fixture"%>
<%@page import="enums.Privilege"%>
<%@page import="model.Team"%>
<%@page import="java.util.List"%>

<%
	Privilege privilege1 = (Privilege) session.getAttribute("privilege");
	Tournament tournament = (Tournament) request.getAttribute("tournament");
	List<Team> teams = (List<Team>) request.getAttribute("teams");
	String yourTournament = (String)request.getAttribute("yourTournament");
	Fixture fixture = tournament.getFixture();
%>

<div id="tournamentProfile">
	<h1><%=tournament.getName()%></h1>
	<%if((privilege1!=null && privilege1.isAdministrador()) || yourTournament=="true"){ %>
	<div class = "pull-right">
	 	<a href="TournamentManager?action=MODIFYTOURNAMENTPAGE" rel="#overlayBig"><img class="icon" src="image/modify.png" width="20" height="20" /></a>&#32;&#32;
        <a href="TournamentManager?action=DELETETOURNAMENT"><img class="icon" src="image/no.png" width="18" height="18" /></a>&#32;&#32;
        <%if(fixture==(null)){%>
        <a href="JSP/team/addTeamOverlay.jsp" rel="#overlayBig"><img class="icon" src="image/addTeam.png" width="20" height="20" /></a>&#32;&#32;

            <%if(teams.size()>=2){ %>
        <form method="post" action="TournamentManager" style="display: inline;">
            <input type="hidden" name="action" value="GENERATEFIXTURE" />
        <button class="btn-primary btn-small" type="submit">Generar Fixture</button>
		</form>
                <%}} %>

    </div>
	
	<%} %>
	
	<div class="tournamentSummary">
		<div class="img">
			<img class="image" src="ImageController?action=TOURNAMENT&value=<%=tournament.getIdTournament()%>&size=250" alt="Tournament Image" />
		</div>
		<div class="summary">
			<div>
				<p><%=tournament.getDescription()%></p>
			</div>
		</div>
	</div>
    <div id="teamList">
        <h2>Equipos</h2>
        <ul id="holder">

        <%if(!teams.isEmpty()){
            for(Team team: teams){
            if(!team.getName().equals("Libre")){
        %>

                <li class="tournamentSummary">
                <%if((privilege1!=null && privilege1.isAdministrador()) || yourTournament=="true"){ %>
                <div class = "pull-right">
	                <a href="TeamManager?action=MODIFYTEAMSERVLET&idTeam=<%=team.getIdTeam()%>" rel="#overlayBig"><img class="icon" src="image/modify.png" width="20" height="20" /></a>&#32;&#32;
	                <%if(fixture==(null)){%>
                    <form id="delete<%=team.getIdTeam()%>" method="post" action="TeamManager" style="display: inline">
                        <input type="hidden" name="action" value="DELETETEAM" />
                        <input type="hidden" name="idTeam" value="<%=team.getIdTeam()%>"/>
	                    <a onclick='document.getElementById("delete<%=team.getIdTeam()%>").submit()' href="#"><img class="icon" src="image/no.png" width="18" height="18" /></a>&#32;&#32;
           	 		</form>
                    <%} %>
           	 	</div>
           	 	<%}%>
                    <form id="team<%=team.getIdTeam()%>" method="post" action="TeamManager">
                        <input type="hidden" name="action" value="TEAMPROFILE" />
                        <input type="hidden" name="idTeam" value="<%=team.getIdTeam()%>"/>

                <div class="img">
                    <img class="image" src="ImageController?action=TEAM&value=<%=team.getIdTeam()%>&size=150" alt="Team Image" />
                </div>

                <div class="summary" style="display: inline;">
                    <div class="title">
                        <a onclick='document.getElementById("team<%=team.getIdTeam()%>").submit()' href="#"><b><%=team.getName()%></b></a>
                    </div>
                    <div>
                        <%=team.getDescription()%>
                    </div>
                </div>
                    </form>
            </li>

        
        <%}}}%>
        </ul>
        <%if(teams.isEmpty()){%>
            <li class="tournamentSummary">
            <p>No Se han ingresado equipos todavia.
                <%if((privilege1!=null && privilege1.isAdministrador()) || yourTournament=="true"){ %>
                    Para hacerlo,  haga click en <img src="image/addTeam.png" width="20" height="20" />
                <%}%>
            </p>
                </li>
        <%}%>

    </div>
</div>

<div class="apple_overlay_big" id="overlayBig">
    <div class="contentWrap"></div>
</div>

	<script type="text/javascript">
		$(document).ready(function() {
			arg.pagination("teamList");
		});
	</script>