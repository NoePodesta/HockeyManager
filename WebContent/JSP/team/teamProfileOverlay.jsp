<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<%@page import="DAO.GoalDao"%>
<%@page import="model.Player"%>
<%@page import="java.util.List"%>
<%@ page import="model.Team" %>
<%@ page import="model.Card" %>
<%@ page import="DAO.CardDao" %>
<%@ page import="enums.CardColour" %>

<div id="teamOverlay">

    <%Team team = (Team)request.getAttribute("team");%>

    <div class="title"><%=team.getName()%></div>

    <div class="teamSummary">
        <div class="img">
            <img class="image" src=ImageController?action=TEAM&value=<%=team.getIdTeam()%>&size=150 alt="Tournament Image" />

        </div>
        <form id="myform" method="post" action="TeamManager">
            <input type="hidden" name="action" value="TEAMPROFILE" />
            <input type="hidden" name="idTeam" value="<%=team.getIdTeam()%>" />
        <div class="summary">
            <div>
                <%if(team.getDescription().getBytes().length > 250){%>
                <%=team.getDescription().substring(0,250)%><a onclick='document.getElementById("myform").submit()' href="#"> <b>...</b></a>
                <%}else{%>
                <%=team.getDescription()%>
                <%}%>
            </div>
        </div>

            
        <div class="info">
            <label style="text-align: left; width: 100%; margin-bottom: 2em;">Players</label>
            <%if(team.getPlayers().isEmpty()){%>
                <p>No se han ingresado jugadores todavía</p>
            <%}else{%>
            <table>
                <thead>
                <tr>
                <td></td>
                <td><img src="image/goles.png" width="15" height="15"/></td>
                <td><img src="image/tarjeta_verde.png" width="15" height="15"/></td>
                <td><img src="image/tarjeta_amarilla.png" width="15" height="15"/></td>
                <td><img src="image/tarjeta_roja.png" width="15" height="15"/></td>
                </tr>
                </thead>
                <tbody>
                <%for(Player player: team.getPlayers()){
                    List<Card> greenCards = CardDao.getCard(CardColour.GREEN, player.getIdplayer());
                    List<Card> yellowCards = CardDao.getCard(CardColour.YELLOW, player.getIdplayer());
                    List<Card> redCards = CardDao.getCard(CardColour.RED, player.getIdplayer());
                %>
                </tr>
                <td style="text-align: left"><p><%=player.getName()%>&nbsp;<%=player.getLastName()%></p></td>
                <td><p><%=player.getGoals().size()%></p></td>
                <td><p><%=greenCards.size()%></p></td>
                <td><p><%=yellowCards.size()%></p></td>
                <td><p><%=redCards.size()%></p></td>
                </tr>
                <%}%>
                </tbody>
            </table>
            <%}%>
        </div>


        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> Más Información</button>
        </div>
        </form>
    </div>

</div>