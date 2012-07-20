<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="model.*" %>

<div id="addGoales">

    <div class="title">Tarjetas Rojas</div>

    <%
        Team localTeam = (Team) request.getAttribute("localTeam");
        Team guestTeam = (Team) request.getAttribute("guestTeam");
        Match match = (Match) request.getAttribute("match");
        List<Card> guestCards = (List<Card>) request.getAttribute("guestRedCard");
        List<Card> localCards = (List<Card>) request.getAttribute("localRedCard");
        List<Player> localTeamPlayers = localTeam.getPlayers();
        List<Player> guestTeamPlayers = guestTeam.getPlayers();

    %>

    <form id="myform" method="post" action="MatchManager">
        <input type="hidden" name="idMatch" value="<%=match.getId_Match()%>"/>
        <input type="hidden" name="action" value="ADDREDCARDS"/>

        <div>
            <div class="colA">
                <div><label class="name" style="color: #4682b4;width: 100%;text-align: center;"><%=localTeam.getName()%>
                </label></div>
                <%
                    if (!localTeamPlayers.isEmpty()) {
                        if (localCards.isEmpty()) {
                            for (int i = 1; i <= 16; i++) {
                %>

                <div class="displayInline">
                    <label>Jugador&nbsp;<%=i%>
                    </label>
                    <select name=local<%=i%>>
                        <option selected value="default">Elige un jugador</option>
                        <%for (Player player : localTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>> <%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}%>
                    </select>

                </div>
                <%
                    }
                } else {
                    int j=0;%>
                <%for (int i = 1; i <= 16; i++) {%>

                <div class="displayInline">
                    <label>Player&nbsp;<%=i%>
                    </label>
                    <select name=local<%=i%>>

                        <%
                            int nLocalGreenCards= localCards.size();
                            if(j<nLocalGreenCards){
                                Player playerGreenCard = localCards.get(j).getPlayer();
                                j++;
                        %>
                        <option value=<%=playerGreenCard.getIdplayer()%> selected><%=playerGreenCard.getName()%>&nbsp;<%=playerGreenCard.getLastName()%>

                                <%for (Player player : localTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}}else{%>
                        <option selected value="default">Elige un jugador</option>
                        <%for (Player player : localTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>



                                <%}}%>
                    </select>
                </div>
                <%
                        }}
                } else {
                %>
                <label><p>No se han ingresado jugadores todavia.</p></label>
                <%
                    }
                %>
            </div>

            <div class="colB">
                <div><label class="name" style="color: #4682b4;width: 100%;text-align: center;"><%=guestTeam.getName()%>
                </label></div>
                <%
                    if (!guestTeamPlayers.isEmpty()) {
                        if (guestCards.isEmpty()) {
                            for (int i = 1; i <= 16; i++) {
                %>

                <div class="displayInline">
                    <label>Jugador&nbsp;<%=i%>
                    </label>
                    <select name=local<%=i%>>
                        <option selected value="default">Elige un jugador</option>
                        <%for (Player player : guestTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>> <%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}%>
                    </select>

                </div>
                <%
                    }
                } else {
                    int j=0;%>
                <%for (int i = 1; i <= 16; i++) {%>

                <div class="displayInline">
                    <label>Player&nbsp;<%=i%>
                    </label>
                    <select name=guest<%=i%>>

                        <%
                            int nGuestGreenCards= guestCards.size();
                            if(j<nGuestGreenCards){
                                Player playerGreenCard = guestCards.get(j).getPlayer();
                                j++;
                        %>
                        <option value=<%=playerGreenCard.getIdplayer()%> selected><%=playerGreenCard.getName()%>&nbsp;<%=playerGreenCard.getLastName()%>

                                <%for (Player player : guestTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}}else{%>
                        <option selected value="default">Elige un jugador</option>
                        <%for (Player player : guestTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>


                                <%}}%>
                    </select>
                </div>
                <%
                        }}
                } else {
                %>
                <label><p>No se han ingresado jugadores todavia.</p></label>
                <%
                    }
                %>
            </div>
        </div>
        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>
    </form>
</div>
