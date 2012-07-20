<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Perfil del Equipo</title>

    <%@include file="/JSP/public/js_includes.jsp" %>
    <%@include file="/JSP/public/css_includes.jsp" %>
</head>
<body>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div class="wrapper">
    <%@include file="/JSP/public/header.jsp" %>
    <div class="content">

        <%@page import="enums.Privilege"%>
        <%@page import="java.util.List"%>
        <%@ page import="model.*" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="DAO.CardDao" %>
        <%@ page import="enums.CardColour" %>

        <%
            Privilege privilege1 = (Privilege) session.getAttribute("privilege");
            Team team = (Team) request.getAttribute("team");
            List<model.Player> players = (List<Player>) request.getAttribute("players");
            String yourTournament = (String)request.getAttribute("yourTournament");
            Fixture fixture = (Fixture) request.getAttribute("fixture");
        %>

        <div id="teamProfile">
            <h1><%=team.getName()%></h1>
            <%if((privilege1!=null && privilege1.isAdministrador()) || yourTournament=="true"){ %>
            <div class = "pull-right">
               <a href="TeamManager?action=MODIFYTEAMSERVLET&idTeam=<%=team.getIdTeam()%>" rel="#overlayBig"><img class="icon" src="image/modify.png" width="20" height="20" /></a>&#32;&#32;
                <a href="JSP/player/addPlayerOverlay.jsp?idTeam=<%=team.getIdTeam()%>" rel="#overlay"><img class="icon" src="image/noee.png" width="20" height="20" /></a>&#32;&#32;
            </div>

            <%}%>

            <div class="teamSummary">
                <div class="img">
                    <img class="image" src="ImageController?action=TEAM&value=<%=team.getIdTeam()%>&size=250" alt="Team Image" />
                </div>
                <div class="summary">
                    <div>
                        <p><%=team.getDescription()%></p>
                    </div>
                </div>
            </div>
            <div id="playerList">
                <h2>Jugadores</h2>
                <ul id="holder">
                <%if(!players.isEmpty()){
                    for(Player player: players){%>

                    <li class="playerSummary">
                        <%if((privilege1!=null && privilege1.isAdministrador()) || yourTournament=="true"){ %>
                        <div class = "pull-right">
                            <a href="PlayerManager?action=MODIFYPLAYERSERVLET&idPlayer=<%=player.getIdplayer()%>" rel="#overlay"><img class="icon" src="image/modify.png" width="20" height="20" /></a>&#32;&#32;
                            <form id="delete<%=player.getIdplayer()%>" method="post" action="PlayerManager" style="display: inline">
                                <input type="hidden" name="action" value="DELETEPLAYER" />
                                <input type="hidden" name="idPlayer" value="<%=player.getIdplayer()%>"/>
                                <a onclick='document.getElementById("delete<%=player.getIdplayer()%>").submit()' href="#"><img class="icon" src="image/no.png" width="18" height="18" /></a>&#32;&#32;
                            </form>
                        </div>
                        <%}%>
                        <div class="img">
                            <img class="image" src="ImageController?action=PLAYER&value=<%=player.getIdplayer()%>&size=150" alt="Team Image" />
                        </div>
                        <div class="summary">
                            <div class="title">
                                <a><b><%=player.getName()%>&#32;<%=player.getLastName()%></b></a>
                            </div>
                            <%if (player.getEmail()!=null){%>
                            <div>
                                <label>Email:&#32;</label><%=player.getEmail()%>
                            </div>
                            <%} if(player.getPosition()!=null){%>
                            <div>
                                <label>Posición:&#32;</label><%=player.getPosition()%>
                            </div>
                            <%}%>
                            <div>
                                <label>Goles:&#32;</label><%=player.getGoals().size()%>
                            </div>
                            <%   List<Card> greenCards = CardDao.getCard(CardColour.GREEN, player.getIdplayer());
                                List<Card> yellowCards = CardDao.getCard(CardColour.YELLOW, player.getIdplayer());
                                List<Card> redCards = CardDao.getCard(CardColour.RED, player.getIdplayer());
                            %>
                            <div>
                                <label>Tarjetas Verdes:&#32;</label><%=greenCards.size()%>
                            </div>
                            <div>
                                <label>Tarjetas Amarillas:&#32;</label><%=yellowCards.size()%>
                            </div>
                            <div>
                                <label>Tarjetas Rojas:&#32;</label><%=redCards.size()%>
                            </div>
                            <%}%>
                        </div>
                    </li>

                <%
                    }
                 else {
                %>
                <li class="playerSummary">
                    <p>No Se han ingresado Jugadores todavia.
                        <%if ((privilege1 != null && privilege1.isAdministrador()) || yourTournament == "true") { %>
                        Para hacerlo, haga click en <img src="image/noee.png" width="20" height="20"/>
                        <%}%>
                    </p>
                </li>
                <%}%>
                </ul>
            </div>
        </div>

    </div>
    <div class="footer">
        <%@include file="/JSP/public/footer.jsp" %>
        <br clear="all" /></div>

</div>

</body>
</html>



