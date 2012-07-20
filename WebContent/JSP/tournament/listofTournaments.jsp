<%@page import="model.Tournament"%>
<%@page import="java.util.List"%>
<div id="tournamentListPage">
<h1>Torneos</h1>
	<ul id="holder">

	<%
		List<Tournament> tournaments = (List<Tournament>) request.getAttribute("tournaments");
     

        if(tournaments.isEmpty()){%>
            <li class="tournamentSummary"> No se han registrado torenos todavia.  </li>
        <%}
		for (Tournament tournament : tournaments) {
            int tournamentId = tournament.getIdTournament();
            String tName = tournament.getName();
            String  tournamentDescription = tournament.getDescription();
	%>

        <form id="myform<%=tournamentId%>" method="post" action="TournamentManager">
            <input type="hidden" name="action" value="REGISTERTOURNAMENT" />
            <input type="hidden" name="value" value="<%=tName%>" />
		<li class="tournamentSummary">
			<div class="img">
				<img class="image" src=ImageController?action=TOURNAMENT&value=<%=tournamentId%>&size=150 alt="Tournament Image" />

			</div>
			<div class="summary">
				<div class="title">
					<a onclick='document.getElementById("myform<%=tournamentId%>").submit()' href="#"><b><%=tName%></b></a>
				</div>
				<div>
                    <%if(tournamentDescription.getBytes().length > 250){%>
					<%=tournament.getDescription().substring(0,250)%><a href="TournamentManager?action=TOURNAMENTPROFILE&value=<%=tournament.getName()%>"> ...</a>
				    <%}else{%>
                    <%=tournament.getDescription()%>
				    <%}%>
                </div>
			</div>
		</li>
        </form>

	<%
		}
	%>
	</ul>
</div>
