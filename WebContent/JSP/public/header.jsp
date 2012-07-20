<%@page import="model.Fixture"%>
<%@page import="enums.Privilege"%>
<%
	Privilege privilege = (Privilege) session.getAttribute("privilege");
	String tournamentName = (String) request.getAttribute("tournamentName");
	String myname = (String) session.getAttribute("username");
	Fixture fixture1 = (Fixture) request.getAttribute("fixture");
%>
<%@include file="/JSP/public/login.jsp"%>
<%@include file="/JSP/user/signUp.jsp"%>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="Home">Hockey Manager</a>
			<div class="nav-links">

			
				<%
					if (myname == null) {
				%>

				<ul class="nav pull-right">
					<li><a href="login" class="signin"><span>Sign in</span></a></li>
					<li class="divider-vertical"></li>
					<li><a href="#signup" class="signup"><span>Registrarse</span></a></li>
				</ul>
					
				
				
					
					<%}else{%>
					
					<ul class="nav pull-right">
                    <li>Hi,<a href="UserManager?action=USERPROFILE" rel="#overlayBig"><%=myname%></a></li>
					<li class="divider-vertical"></li>
					<li><a href="Logout">Logout</a></li>

				</ul>
				<%
					}
				%>
					
					
					<ul class="nav pull-left">

						<%if (tournamentName != null) {
					%>
                        <form id="tournament" method="post" action="TournamentManager">
                            <input type="hidden" name="action" value="TOURNAMENTPROFILE" />
                            <input type="hidden" name="value" value="<%=tournamentName%>" />

					<li><a href="#" onclick='document.getElementById("tournament").submit()'><span><%=tournamentName%></span></a></li>
                            </form>
                        <form id="commentario" method="post" action="Comment">
                            <input type="hidden" name="action" value="SHOWCOMMENT" />
                            <input type="hidden" name="value" value="<%=tournamentName%>" />

                        <a href="#" onclick='document.getElementById("commentario").submit()'><span>Deja Tu Comentario!</span></a></li>
                        
						</form>
                                <%if (fixture1 != null) {%>
                        <form id="fixturee" method="post" action="TournamentManager">
                            <input type="hidden" name="action" value="FIXTUREPAGE" />
                            <input type="hidden" name="value" value="<%=tournamentName%>" />
                            <li><a href="#" onclick='document.getElementById("fixturee").submit()'><span>Fixture</span></a></li>
                        </form>
                        <form id="positions" method="post" action="TournamentManager">
                            <input type="hidden" name="action" value="POSITIONPAGE" />
                            <input type="hidden" name="value" value="<%=tournamentName%>" />
                            <li><a href="#" onclick='document.getElementById("positions").submit()'><span>Posiciones</span></a></li>
                        </form>
                        <form id="results" method="post" action="TournamentManager">
                            <input type="hidden" name="action" value="RESULSTPAGE" />
                            <input type="hidden" name="value" value="<%=tournamentName%>" />
                            <li><a href="#" onclick='document.getElementById("results").submit()'><span>Resultados</span></a></li>
                        </form>
					<%		}
						}
						else if ((tournamentName == null)  && (privilege != null) && (privilege.isUserAdmin() || privilege.isAdministrador())){
						%>
						<li><a href="JSP/tournament/newTournament.jsp" rel="#overlay"><span>REGISTRAR
									TORNEO</span></a></li>
						<%
							}
						%>

					

				</ul>
				




			</div>

		</div>
	</div>
</div>


<div class="apple_overlay" id="overlay" style="width: 450px;">
	<!-- the external content is loaded inside this tag -->
	<div class="contentWrap"></div>
</div>
<div class="apple_overlay" id="overlayBig" style="width: 450px;">
    <!-- the external content is loaded inside this tag -->
    <div class="contentWrap"></div>
</div>

	<script type="text/javascript">
		$(document).ready(function() {
			arg.signin();
			arg.signup();
		});
	</script>

