
<%@page import="enums.Privilege"%>
<%@page import="model.Fixture"%>
<%
	String privilege = (String) session.getAttribute("privilege");
	String tournamentName = (String) session.getAttribute("tournament");
	String myname = (String) session.getAttribute("username");
	Fixture fixture1 = (Fixture) session.getAttribute("fixture");
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
					<%@include file="/JSP/public/search.jsp"%>
					<li><a href="login" class="signin"><span>Sign in</span></a></li>
					<li class="divider-vertical"></li>
					<li><a href="#signup" class="signup"><span>Registrarse</span></a></li>
				</ul>
					
				
				
					
					<%}else{%>
					<ul class="nav pull-left">
						<%if (tournamentName != null) {
					%>
					<li><a href="TournamentManager?action=TOURNAMENTPROFILE&value=<%=tournamentName%>"><span><%=tournamentName%></span></a></li>
						<%if (fixture1 != null) {%>
					<li><a href="TournamentManager?action=FIXTUREPAGE&value=<%=tournamentName%>"><span>Fixture</span></a></li>
					<li><a href="TournamentManager?action=POSITIONPAGE&value=<%=tournamentName%>"><span>Posiciones</span></a></li>
					<li><a href="TournamentManager?action=RESULSTPAGE&value=<%=tournamentName%>"><span>Resultados</span></a></li>
					<%		}
						}
						else if ((tournamentName == null)  && (privilege.equalsIgnoreCase(Privilege.USERADMIN.getValue()) || privilege.equalsIgnoreCase(Privilege.USERADMIN.getValue()))){
						%>
						<li><a href="JSP/tournament/newTournament.jsp" rel="#overlay"><span>REGISTRAR
									TORNEO</span></a></li>
						<%
							}
						%>

					

				</ul>
				<ul class="nav pull-right">
					<%@include file="/JSP/public/search.jsp"%>
					<li>Hi,<a href="UserManager?action=USERPROFILE" rel="#overlay"><%=myname%></a></li>
					<li class="divider-vertical"></li>
					<li><a href="Logout">Logout</a></li>

				</ul>
				<%
					}
				%>




			</div>

		</div>
	</div>
</div>


<div class="apple_overlay" id="overlay" style="width: 450px;">
	<!-- the external content is loaded inside this tag -->
	<div class="contentWrap"></div>
</div>


	<script type="text/javascript">
		$(document).ready(function() {
			arg.signin();
			arg.signup();
		});
	</script>

