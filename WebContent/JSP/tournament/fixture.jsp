<%@page import="model.Match"%>
<%@page import="enums.Privilege"%>
<%
    Privilege privilege1 = (Privilege) session.getAttribute("privilege");
    String yourTournament = (String)request.getAttribute("yourTournament");
%>

<div id="fixture">

    <div class="pull-right">
        <a class="print" onclick="window.print();" value="imprimir"><img src="image/printer.png" width="20" height="20" /></a>
    </div>

    <h1 class="title">Fixture</h1>

    <table id="fixtureTable">

		<%@page import="java.util.Date"%>
		<%@page import="java.util.List"%>


        <thead>
        <tr>
            <th style="width: 2%">Fecha</th>
            <th style="width: 20%">Dia</th>
            <th style="width: 10%">Local</th>
            <th style="width: 2%;">VS</th>
            <th style="width: 10%;">Visitante</th>
        </tr>
        </thead>

        <tbody id="tbody">
        <%

            List<Match> matches = (List<Match>) request.getAttribute("matches");
            System.out.println(matches);
            for (int i = 0; i < matches.size(); i++) {
                Team local = matches.get(i).getLocal();
                String localTeam = local.getName();
                Team guest = matches.get(i).getGuest();
                String guestTeam = guest.getName();
                int idMatch = matches.get(i).getId_Match();
                Date date = matches.get(i).getDate();
                int fecha = matches.get(i).getFecha();


        %>
		<tr>
			<td><%=fecha%></td>
			<td>
				<%if(date==null){
                    if((privilege1!=null && privilege1.isAdministrador()) || yourTournament=="true"){ %>
                        <a href="JSP/match/date.jsp?idMatch=<%=idMatch%>"
				        rel="#overlaySmall" style="text-decoration: none"><img
					    src="image/cale.png" width="20" height="20" /></a>
                    <%}else{%>
                        ?
				<%}}else{%>
                    <%=date%>
				<%}%>
			</td>
			<td>
				<%if (localTeam.equals("Libre")){%><span><%=localTeam%></span>
				<%}else{%><a href="TeamManager?action=TEAMPROFILEOVERLAY&idTeam=<%=local.getIdTeam()%>"
                               rel="#overlayBig" style="color: gray;"><%=localTeam%></a>
				<%}%>
			</td>
			<td></td>
            <td>
				<%if (guestTeam.equals("Libre")){%><span><%=guestTeam%></span>
				<%}else{%><a href="TeamManager?action=TEAMPROFILEOVERLAY&idTeam=<%=guest.getIdTeam()%>"
                             rel="#overlayBig" style="color: gray;"><%=guestTeam%></a>
				<%}%>
			</td>
		</tr>
        <%}%>
        </tbody>
	</table>


	<div class="displayRight">
		<p>
            <img src="image/cale.png" width="10" height="10"/> Seleccione una fecha,&nbsp;
            <img src="image/ver.gif" width="10" height="10"/> Información sobre el partido,&nbsp;
		</p>
	</div>
</div>


<div class="apple_overlay_small" id="overlaySmall">
	<div class="contentWrap"></div>
</div>
<div class="apple_overlay_small" id="overlayBig">
    <div class="contentWrap"></div>
</div>


