<%@page import="model.Match" %>
<%@page import="model.Player" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.List" %>
<%@page import="enums.Privilege" %>
<%
    Privilege privilege1 = (Privilege) session.getAttribute("privilege");
    String yourTournament = (String) request.getAttribute("yourTournament");
%>

<div id="tournamentResults">

    <div class="pull-right">
        <a class="print" onclick="window.print();" value="imprimir"><img src="image/printer.png" width="20"
                                                                         height="20"/></a>
    </div>

    <h1 class="title">Resultados</h1>

    <table id="resultsTable">

        <thead>
        <tr>
            <th style="width: 5%">Fecha</th>
            <th style="width: 20%">Dia</th>
            <th style="width: 10%">Info</th>
            <th style="width: 20%">Local</th>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 20%">Visitante</th>
            <%if ((privilege1 != null && privilege1.isAdministrador()) || yourTournament == "true") { %>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 5%">&nbsp;</th>
            <th style="width: 5%">&nbsp;</th>
            <%}%>
        </tr>
        </thead>

        <tbody id="tbody">
        <%


            List<Match> matches = (List<Match>) request.getAttribute("matches");
            System.out.println(matches);
            for (int i = 0; i < matches.size(); i++) {
                Team local = matches.get(i).getLocal();
                String localTeam = local.getName();
                List<Player> localPlayers = local.getPlayers();
                Team guest = matches.get(i).getGuest();
                String guestTeam = guest.getName();
                List<Player> guestPlayers = guest.getPlayers();
                int idMatch = matches.get(i).getId_Match();
                Date date = matches.get(i).getDate();
                int localResult = matches.get(i).getResultLocal();
                int guestResult = matches.get(i).getResultGuest();
                int fecha = matches.get(i).getFecha();


        %>


        <tr>
            <td><%=fecha%>
            </td>
            <td>
                <%
                    if (date == null) {
                        if ((privilege1 != null && privilege1.isAdministrador()) || yourTournament == "true") {
                %>
                <a href="JSP/match/date.jsp?idMatch=<%=idMatch%>"
                   rel="#overlaySmall" style="text-decoration: none"><img
                        src="image/cale.png" width="20" height="20"/></a>
                <%} else {%>
                ?
                <%
                    }
                } else {
                %>
                <%=date%>
                <%}%>
            </td>

            <td><%if (localResult != -1 && guestResult != -1) {%><a
                    href="MatchManager?action=OVERVIEW&idMatch=<%=idMatch%>" rel="#overlayBig"><img src="image/search.png"
                                                                                                 width="20"
                                                                                                 height="20"/></a></td>
            <%}%>
            <td><%if (localTeam.equals("Libre")) {%><span><%=localTeam%>
            </span><%} else {%><a href="TeamManager?action=TEAMPROFILEOVERLAY&idTeam=<%=local.getIdTeam()%>"
                rel="#overlayBig" style="color: gray;"><%=localTeam%></a><%}%></td>
            <td><%if (localResult == -1 && guestResult == -1) {%> <%} else {%><%=localResult%><%}%></td>
            <td><span>-</span></td>
            <td><%if (localResult == -1 && guestResult == -1) {%> <%} else {%><%=guestResult%><%}%></td>
            <td><%if (guestTeam.equals("Libre")) {%><span><%=guestTeam%></span><%} else {%>
                <a href="TeamManager?action=TEAMPROFILEOVERLAY&idTeam=<%=guest.getIdTeam()%>" rel="#overlayBig" style="color: gray;"><%=guestTeam%></a><%}%></td>
            <%if ((privilege1 != null && privilege1.isAdministrador()) || yourTournament == "true") { %>
            <td><%if (localResult != -1 && guestResult != -1) {%>
                <div class="link"><a href="MatchManager?action=MODIFYRESULTSERVLET&idMatch=<%=idMatch%>"
                                     rel="#overlaySmall" style="color: gray;"> Modificar resultado</a></div>
                <%
                } else {
                    if ((localTeam.equals("Libre")) || (guestTeam.equals("Libre"))) {
                %><%} else {%>
                <div class="link"><a href="MatchManager?action=RESULTSERVLET&idMatch=<%=idMatch%>" rel="#overlaySmall">
                    Subir resultado</a></div>
            </td>
            <%
                    }
                }
            %>
            <%if (localResult != -1 && guestResult != -1) {%>
            <td><%if (!(localPlayers.isEmpty()) || !(guestPlayers.isEmpty())) {%><a
                    href="MatchManager?action=ADDGOALESSERVLET&idMatch=<%=idMatch%>" rel="#overlayMedium"
                    style="text-decoration: none"><img src="image/goles.png" width="15" height="15"/></a><%}%></td>
            <td><%if (!(localPlayers.isEmpty()) || !(guestPlayers.isEmpty())) {%><a
                    href="MatchManager?action=CARDSERVLET&idMatch=<%=idMatch%>&colorCard=RED" rel="#overlayMedium"
                    ><img src="image/tarjeta_roja.png" width="10" height="10"/></a><%}%>
            </td>
            <td><%if (!(localPlayers.isEmpty()) || !(guestPlayers.isEmpty())) {%><a
                    href="MatchManager?action=CARDSERVLET&idMatch=<%=idMatch%>&colorCard=YELLOW" rel="#overlayMedium"
                    ><img src="image/tarjeta_amarilla.png" width="10"
                          height="10"/></a><%}%></td>
            <td style="padding-right: 10px"><%if (!(localPlayers.isEmpty()) || !(guestPlayers.isEmpty())) {%><a
                    href="MatchManager?action=CARDSERVLET&idMatch=<%=idMatch%>&colorCard=GREEN" rel="#overlayMedium"
                    ><img src="image/tarjeta_verde.png" width="10" height="10"/></a><%}%>
            </td>
            <%} else {%>
            <td></td>
            <td></td>
            <td></td>
            <td style="padding-right: 10px"></td>

            <%
                    }
                }
            %>
        </tr>
        <%}%>
        </tbody>
    </table>

    <div class="displayRight">
        <p>
            <img src="image/cale.png" width="10" height="10"/> Seleccione una fecha,&nbsp;
            <img src="image/ver.gif" width="10" height="10"/> Información sobre el partido,&nbsp;
            <img src="image/goles.png" width="10" height="10"/> Agregue los goleadores del partido,&nbsp;
            <img src="image/tarjeta_verde.png" width="10" height="10"/> Agregue las tarjetas verdes del partido,&nbsp;
            <img src="image/tarjeta_amarilla.png" width="10" height="10"/> Agregue las tarjetas amarillas del partido,&nbsp;
            <img src="image/tarjeta_roja.png" width="10" height="10"/> Agregue las tarjetas rojas del partido.
        </p>
    </div>
</div>

<div class="apple_overlay_small" id="overlaySmall">
    <div class="contentWrap"></div>
</div>
<div class="apple_overlay_medium" id="overlayMedium">
    <div class="contentWrap"></div>
</div>

<div class="apple_overlay_big" id="overlayBig">
    <div class="contentWrap"></div>
</div>


