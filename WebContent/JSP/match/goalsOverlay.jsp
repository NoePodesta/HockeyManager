<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<%@page import="model.Match" %>
<%@page import="model.Player" %>
<%@page import="model.Team" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Goal" %>

<div id="addGoales">

    <div class="title">Goles</div>

    <%
        Team localTeam = (Team) request.getAttribute("localTeam");
        Team guestTeam = (Team) request.getAttribute("guestTeam");
        Match match = (Match) request.getAttribute("match");
        List<Goal> localGoales = (List<Goal>)request.getAttribute("localGoales");
        List<Goal> guestGoales = (List<Goal>)request.getAttribute("guestGoales");
        List<Player> localTeamPlayers = localTeam.getPlayers();
        List<Player> guestTeamPlayers = guestTeam.getPlayers();
    %>

    <form id="myform" method="post" action="MatchManager">
        <input type="hidden" name="action" value="ADDGOALS"/>
        <input type="hidden" name="idMatch" value="<%=match.getId_Match()%>"/>
        <div>
            <div class="colA">
                <div><label  class="name" style="color: #4682b4;width: 100%;text-align: center;"><%=localTeam.getName()%>
                </label></div>
                <%if (match.getResultLocal() == 0) {%>
                <label><p>No hubieron goles</p></label>
                <%
                } else {
                    if(localGoales.isEmpty()){
                    if (!localTeamPlayers.isEmpty()) {
                %>
                <%for (int i = 1; i <= match.getResultLocal(); i++) {%>
                <div class="displayInline">
                    <label>Goal&nbsp;<%=i%>
                    </label>
                        <select name=local<%=i%>>
                        <option value=default selected>Elige un jugador</option>
                        <%for (Player player : localTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>> <%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}%>
                        </select>

                </div>
                <%
                        }

                }else{%>
                <label><p>No han ingresado jugadores todavia.</p></label>
                <%
                    }}else{
                    int j=0;%>
                <%for (int i = 1; i <= match.getResultLocal(); i++) {%>
                <div class="displayInline">
                    <label>Goal&nbsp;<%=i%>
                    </label>
                    <select name=local<%=i%>>

                        <%
                            int nGuestGoals= guestGoales.size();
                            if(j<=nGuestGoals){
                                Player playerGoal = localGoales.get(j).getPlayer();
                                j++;
                        %>
                        <option value=<%=playerGoal.getIdplayer()%> selected><%=playerGoal.getName()%>&nbsp;<%=playerGoal.getLastName()%>

                                <%for (Player player : localTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>
                            <%}}else{%>
                        <option value=default selected>Elige un jugador</option>
                        <%for (Player player : localTeamPlayers) {%>

                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>


                                <%}}%>
                    </select>
                </div>
                <%
                            }}}
                %>
            </div>

            <div class="colB">
                <div><label class="name" style="color: #4682b4;width: 100%;text-align: center;"><%=guestTeam.getName()%></label></div>
                <%if (match.getResultGuest() == 0) {%>
                <label><p>No hubieron goles</p></label>
                <%
                } else {
                    if(guestGoales.isEmpty()){
                        if (!guestTeamPlayers.isEmpty()) {
                %>
                <%for (int i = 1; i <= match.getResultGuest(); i++) {%>
                <div class="displayInline">
                    <label>Goal&nbsp;<%=i%>
                    </label>
                    <select name=guest<%=i%>>
                        <option value=default selected>Elige un jugador</option>
                        <%for (Player player : guestTeamPlayers) {%>
                        <option value=<%=player.getIdplayer()%>> <%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}%>
                    </select>

                </div>
                <%
                    }

                }else{%>
                <label><p>No han ingresado jugadores todavia.</p></label>
                <%
                    }}else{ 
                    	int j=0;%>
                <%for (int i = 1; i <= match.getResultGuest(); i++) {%>
                <div class="displayInline">
                    <label>Goal&nbsp;<%=i%>
                    </label>
                    <select name=guest<%=i%>>
                    	
                        <%
                        int nGuestGoals= guestGoales.size(); 
                        if(j<nGuestGoals){  
                        Player playerGoal = guestGoales.get(j).getPlayer();               
                        j++;   
                        %>
                        <option value=<%=playerGoal.getIdplayer()%> selected><%=playerGoal.getName()%>&nbsp;<%=playerGoal.getLastName()%>
                        
                        <%for (Player player : guestTeamPlayers) {%>

                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>
                                <%}}else{%>
                        <option value=default selected>Elige un jugador</option>
                            <%for (Player player : guestTeamPlayers) {%>

                        <option value=<%=player.getIdplayer()%>><%=player.getName()%>&nbsp;<%=player.getLastName()%>


                                <%}}%>

                    </select>
                </div>
                <%
                            }}}
                %>
            </div>
            <div class="pull-right">
                <button class="btn-primary btn-small" type="submit"> OK</button>
            </div>
        </div>

    </form>
</div>
