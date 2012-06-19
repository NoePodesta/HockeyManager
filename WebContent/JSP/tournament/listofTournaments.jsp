<%@page import="model.Tournament"%>
<%@page import="java.util.List"%>
<div id="tournamentListPage">
<h1>Tournaments</h1>
	<ul id="holder">
	<%
		List<Tournament> tournaments = (List<Tournament>) request.getAttribute("tournaments");
		for (Tournament tournament : tournaments) {
	%>

	
		<li class="tournamentSummary">
			<div class="img">
				<img class="userImg" src=ImageController?action=TOURNAMENT&value=<%=tournament.getIdTournament()%> alt="Tournament Image" />
		
			</div>
			<div class="summary">
				<div class="title">
					<a href="TournamentManager?action=TOURNAMENTPROFILE&value=<%=tournament.getName()%>"><b><%=tournament.getName()%></b></a>
				</div>
				<div>
					<%=tournament.getDescription()%>
				</div>
			</div>
		</li>
	
	<%
		}
	%>
	</ul>
</div>
