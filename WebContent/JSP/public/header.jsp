
<%@page import="enums.Privilege"%>
<%@page import="model.Fixture"%>
<%
	String privilege = (String) session.getAttribute("privilege") ;
	String myname = (String) session.getAttribute("username");
	String tournament = (String) session.getAttribute("tournament");
	Fixture fixture1 = (Fixture) session.getAttribute("fixture");
%>
<%@include file="/JSP/public/login.jsp"%>
<%@include file="/JSP/user/signUp.jsp"%>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="Home">Hockey Manager</a>
			<div class="nav-links">
				<%if (myname == null) { %>
				<ul class ="nav pull-left">
					<li><a href="Home">Home</a></li>
								
				</ul>

				<ul class="nav pull-right">
					<%@include file="/JSP/public/search.jsp" %>		
					<li><a href="login" class="signin"><span>Sign in</span></a></li>
					<li class="divider-vertical"></li>
					<li><a href="#signup" class="signup"><span>Registrarse</span></a></li>
				</ul>
				
				<%}else if (privilege.equalsIgnoreCase(Privilege.ADMINISTRADOR.getValue())
						|| privilege.equalsIgnoreCase(Privilege.USERADMIN.getValue())){%>

				<ul class ="nav pull-left">
					<li><a href="UserManager?action=USERPROFILE" rel="#overlay"><span>Perfil</span></a></li>
					<%if (tournament!=null) {%>
						<li><a href="TournamentManager?action=TOURNAMENTPROFILE"><span><%=tournament%></span></a></li>
						<li><a href="TournamentManager?action=TEAMSTOURNAMENT"><span>Equipos</span></a></li>
						<%if(fixture1!=null){ %>
							<li><a href="TournamentManager?action=FIXTUREPAGE"><span>Fixture</span></a></li>
							<li><a href="TournamentManager?action=POSITIONPAGE"><span>Posiciones</span></a></li>
							<li><a href="TournamentManager?action=RESULSTPAGE"><span>Resultados</span></a></li>
						<%}
					}else{%>
						<li><a href="TournamentManager?action=CREATETOURNAMENTPAGE"><span>CREAR TORNEO</span></a></li>
									
					<%}%>
					
				</ul>					
				<ul class="nav pull-right">
					<%@include file="/JSP/public/search.jsp" %>
					<li><a href="Logout">Logout</a></li>
				</ul>

				<%}else if (privilege.equalsIgnoreCase(Privilege.USER.getValue())){%>
				<ul class="nav pull-left">
					<li><a href="UserManager?action=USERPROFILE" rel="#overlay">Perfil</a></li>
					
				</ul>
					
				<ul class="nav pull-right">
					<%@include file="/JSP/public/search.jsp" %>
					<li><a href="Logout">Logout</a></li>
				</ul>
				<%}%>
				
				
			
			</div>

		</div>
	</div>
</div>


<div class="apple_overlay" id="overlay">
	<div class="contentWrap"></div>
</div>
