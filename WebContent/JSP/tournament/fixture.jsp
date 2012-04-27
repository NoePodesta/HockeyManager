
<%@page import="model.Fixture"%>

<div class="scroll-pane">
	<table class="fixture" style="width: ;">

		<%@page import="java.util.List"%>
		<%@page import="java.util.Date"%>
		<%@page import="model.Match"%>
		<%@page import="model.Player"%>
		<%@page import="model.Team"%>

		<thead>
		<tr class="subtitulo">
			<th style="width: 5px">Fecha</th>
			<th style="width: 10px">Dia</th>
			<th style="width: 10px">Local</th>
			<th style="width: 10px;">VS</th>
			<th style="width: 5px;">Visitante</th>
		</tr>
		<thead>
		<%
			
			List<Match> matches = (List<Match>) request.getAttribute("matches"); 
			System.out.println(matches);
			for (int i = 0; i < matches.size(); i++) {
		 	String localteam = matches.get(i).getLocal().getName();
		 	String guestteam = matches.get(i).getGuest().getName();
		 	int idmatch = matches.get(i).getId_Match();
	 		Date date = matches.get(i).getDate();
	 		int resultlocal = matches.get(i).getResultLocal();
	 		int resultguest = matches.get(i).getResultGuest();
	 		int fecha = matches.get(i).getFecha();
	 	
					
	 		 %>


		<tr>
			<th style="width: 5%"><%=fecha%></th>
			<th style="width: 5%">
				<%if(date==null){%><a href="date.jsp?idmatch=<%=idmatch%>"
				rel="#overlay3" style="text-decoration: none"><img
					src="image/cale.png" width="20" height="20" /></a>
				<%}else{%> <%=date%>
				<%}%>
			</th>
			<th style="width: 5%">
				<%if (resultlocal!=-1 && resultguest!=-1){%><a
				href="MatchManager?action=OVERVIEW&idmatch=<%=idmatch%>"
				rel="#overlay" style="text-decoration: none">hola</a>
			</th>
			<%} %>
			<th style="width: 5%">
				<%if(resultlocal!=-1 && resultguest !=-1){%><a
				href="MatchManager?action=OVERVIEW&idmatch=<%=idmatch%>"
				rel="#overlay" style="text-decoration: none"><img
					src="image/search.png" width="20" height="20" "/></a>
			</th>
			<%}%>
			<th>
				<%if (localteam.equals("Libree")){%><p><%=localteam%></p>
				<%}else{%><%=localteam%>
				<%}%>
			</th>
			<th></th>
			<th>
				<%if (guestteam.equals("Libree")){%><span><%=guestteam%></span>
				<%}else{%><%=guestteam%>
				<%}%>
			</th>
			<th style="border-bottom: none"></th>

		</tr>
		<%}%>
	</table>

	<div class="displayRight">
		<p>
			<img src="image/cale.png" width="10" height="10" /> Seleccione una
			fecha, <img src="image/ver.gif" width="10" height="10" /> Información
			sobre el partido
		</p>
	</div>
</div>


<div class="apple_overlay" id="overlay3">
	<div class="contentWrap"></div>
</div>

<div class="apple_overlay" id="overlay">
	<div class="contentWrap"></div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		arg.linkDisplay();
	});
</script>

