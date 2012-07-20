<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="model.*" %>

<div id="overview">

    <div class="title">Preview</div>

        <%
        Match match = (Match) request.getAttribute("match");
        List<Card> guestGreenCards = (List<Card>) request.getAttribute("guestGreenCards");
        List<Card> guestYellowCards = (List<Card>) request.getAttribute("guestYellowCards");
        List<Card> guestRedCards = (List<Card>) request.getAttribute("guestRedCards");
        List<Card> localGreenCards = (List<Card>) request.getAttribute("guestGreenCards");
        List<Card> localYellowCards = (List<Card>) request.getAttribute("guestYellowCards");
        List<Card> localRedCards = (List<Card>) request.getAttribute("guestRedCards");
        List<Goal> localGoals = (List<Goal>) request.getAttribute("localGoals");
        List<Goal> guestGoals = (List<Goal>) request.getAttribute("guestGoals");
    %>


    <div class="colA">
        <div>
            <label  class="name" style="color: #4682b4;width:100%;text-align: center;"><%=match.getLocal().getName()%>
            </label>
            <label style="text-align: right;width:100%;height: 3em"><%=match.getResultLocal()%>&nbsp;<span>-</span>&nbsp;
            </label>

        </div>
        <div>
            <label style="text-align:left; width:100%;"><img src="image/goles.png" width="15" height="15"/>&nbsp;Goles&nbsp;</label>
        </div>
        <%
            for (Goal goal : localGoals) {
                Player player = goal.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(localGoals.isEmpty()){%>
        <div>
            <p>No se han ingresado goles</p>
        </div>
        <%}%>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/tarjeta_verde.png" width="10"
                                                             height="10"/>&nbsp;Tarjetas Verdes&nbsp;</label>
        </div>

        <%
            for (Card card : localGreenCards) {
                Player player = card.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(localGreenCards.isEmpty()){%>
            <div>
            <p>No se han ingresado tarjetas</p>
            </div>
        <%}%>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/tarjeta_amarilla.png" width="10"
                                                             height="10"/>&nbsp;Tarjetas Amarillas&nbsp;</label>
        </div>
        <%
            for (Card card : localYellowCards) {
                Player player = card.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(localYellowCards.isEmpty()){%>
        <div>
            <p>No se han ingresado tarjetas</p>
        </div>
        <%}%>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/tarjeta_roja.png" width="10"
                                                             height="10"/>&nbsp;Tarjetas Rojas&nbsp;</label>
        </div>
        <%
            for (Card card : localRedCards) {
                Player player = card.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(localRedCards.isEmpty()){%>
        <div>
            <p>No se han ingresado tarjetas</p>
        </div>
        <%}%>
    </div>
    <div class="colB">
        <div>
            <label  class="name" style="color: #4682b4;width:100%;text-align: center;"><%=match.getGuest().getName()%>
            </label>
            <label style="text-align: left; width:100%;height: 3em"><%=match.getResultLocal()%>
            </label>
        </div>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/goles.png" width="15" height="15"/>&nbsp;Goles&nbsp;</label>
        </div>
        <%
            for (Goal goal : guestGoals) {
                Player player = goal.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(guestGoals.isEmpty()){%>
        <div>
            <p>No se han ingresado goles</p>
        </div>
        <%}%>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/tarjeta_verde.png" width="10"
                                                             height="10"/>&nbsp;Tarjetas Verdes&nbsp;</label>
        </div>
        <%
            for (Card card : guestGreenCards) {
                Player player = card.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(guestGreenCards.isEmpty()){%>
        <div>
            <p>No se han ingresado tarjetas</p>
        </div>
        <%}%>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/tarjeta_amarilla.png" width="10"
                                                             height="10"/>&nbsp;Tarjetas Amarillas&nbsp;</label>
        </div>
        <%
            for (Card card : guestYellowCards) {
                Player player = card.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(guestYellowCards.isEmpty()){%>
        <div>
            <p>No se han ingresado tarjetas</p>
        </div>
        <%}%>
        <div>
            <label style="text-align: left;width: 100%"><img src="image/tarjeta_roja.png" width="10"
                                                             height="10"/>&nbsp;Tarjetas Rojas&nbsp;</label>
        </div>
        <%
            for (Card card : guestRedCards) {
                Player player = card.getPlayer();
        %>
        <div><p><%=player.getName()%>&nbsp;<%=player.getLastName()%>
        </p></div>
        <%}if(guestRedCards.isEmpty()){%>
        <div>
            <p>No se han ingresado tarjetas</p>
        </div>
        <%}%>
    </div>

