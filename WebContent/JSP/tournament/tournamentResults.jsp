<div class="scroll-pane"> 
	<table class="fixture" style="width: ;">
		
		<%@page import="java.util.List"%>
		<%@page import= "java.util.Date"%> 
		<%@page import="model.Match"%> 
			<%@page import="model.Player"%>
			<%@page import="model.Team"%>
			
			<tr class="subtitulo">
				<th style="width: 5%">Fecha</th>
				<th style="width: 10%">Dia</th>
				<th style="width: 10%">Info</th>
			<th style="width: 10">Local</th>
			<th style="width: 10%;">&nbsp;</th>
			<th style="width: 5%;">Visitante</th>
		</tr>
			
			<%
			
			List<Match> matches = (List<Match>) request.getAttribute("matches"); 
			System.out.println(matches);
			for (int i = 0; i < matches.size(); i++){
		 	String localteam = matches.get(i).getLocal().getName();
		 	List<Player> localplayers = matches.get(i).getLocal().getPlayers();
		 	String guestteam = matches.get(i).getGuest().getName();
	 		List<Player> guestplayers = matches.get(i).getGuest().getPlayers();
	 		int idmatch = matches.get(i).getId_Match();
	 		Date date = matches.get(i).getDate();
	 		int resultlocal = matches.get(i).getResultLocal();
	 		int resultguest = matches.get(i).getResultGuest();
	 		int fecha = matches.get(i).getFecha();

					
	 		 %>
		
	
		<tr>
			<th style="width: 5%"><%=fecha%></th>
				<th style="width: 5%"><%if(date==null){%><a href="date.jsp?idmatch=<%=idmatch%>" rel="#overlay3" style="text-decoration: none"><img src="image/cale.png" width="20" height="20" /></a><%}else{%> <%=date%><%}%></th>
				<th style="width: 5%"><%if(resultlocal!=-1 && resultguest !=-1){%><a href="MatchManager?action=OVERVIEW&idmatch=<%=idmatch%>" rel="#overlay" style="text-decoration: none"><img src="image/search.png" width="20" height="20""/></a></th><%}%>
				<th><%if (localteam.equals("Libree")){%><p><%=localteam%></p><%}else{%><%=localteam%><%}%></th>
			<th><%if(resultlocal==-1 && resultguest ==-1){%> <%}else{%><%=resultlocal%> - <%=resultguest%><%}%></th>
			<th><%if (guestteam.equals("Libree")){%><span><%=guestteam%></span><%}else{%><%=guestteam%><%}%></th>
			<th style="border-bottom: none"></th>
			<th style="width: 5%; border-bottom: none"><%if(resultlocal!=-1 && resultguest !=-1){%><div class="link"><a href="MatchManager?action=MODIFYRESULTSERVLET&idmatch=<%=idmatch%>" rel="#overlay" style="text-decoration: none"> Modificar resultado</a></div><%}else{ if((localteam.equals("Libree")) || (guestteam.equals("Libree"))){%><%}else{%><div class="link"><a href="MatchManager?action=RESULTSERVLET&idmatch=<%=idmatch%>" rel="#overlay" style="text-decoration: none"> Subir resultado</a></div></th><%}}%>
			<th style=" border-bottom: none"><%if(!(localplayers.isEmpty()) || !(guestplayers.isEmpty())){%><a href="MatchManager?action=ADDGOALESSERVLET&idmatch=<%=idmatch%>" rel="#overlay" style="text-decoration: none"><img src="image/goles.png" width="15" height="15" /></a><%}%></th>
			<th style=" border-bottom: none"><%if(!(localplayers.isEmpty()) || !(guestplayers.isEmpty())){%><a href="MatchManager?action=CARDSERVLET&idmatch=<%=idmatch%>&idpage=3" rel="#overlay" style="text-decoration: none"><img src="image/tarjeta_roja.png" width="10" height="10" /></a><%}%></th>
			<th style=" border-bottom: none"><%if(!(localplayers.isEmpty()) || !(guestplayers.isEmpty())){%><a href="MatchManager?action=CARDSERVLET&idmatch=<%=idmatch%>&idpage=2" rel="#overlay" style="text-decoration: none"><img src="image/tarjeta_amarilla.png" width="10" height="10" /></a><%}%></th>
			<th style=" border-bottom: none"><%if(!(localplayers.isEmpty()) || !(guestplayers.isEmpty())){%><a href="MatchManager?action=CARDSERVLET&idmatch=<%=idmatch%>&idpage=1" style="text-decoration: none"><img src="image/tarjeta_verde.png" width="10" height="10" /></a><%}%></th>		
		</tr>
	<%}%>
	</table>
	
<div class="displayRight">
<p><img src="image/cale.png" width="10" height="10" /> Seleccione una fecha, <img src="image/ver.gif" width="10" height="10"/> Información sobre el partido</p>
</div>
</div> 

<div class="apple_overlay" id="overlay">
	<div class="contentWrap"></div>
</div>
<div class="apple_overlay" id="overlay2">
	<div class="contentWrap"></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		arg.linkDisplay();																					
	});
</script>

