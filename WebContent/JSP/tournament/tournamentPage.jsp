<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Perfil del Torneo</title>

<%@include file="/JSP/public/js_includes.jsp" %>
<%@include file="/JSP/public/css_includes.jsp" %>
</head>
<body>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
		<div class="wrapper">
			<%@include file="/JSP/public/header.jsp" %>
							<div class="content">
	
		<%if(request.getAttribute("page").equals("profileTournament")){%>		
						<%@include file="/JSP/tournament/profileTournament.jsp"%>
		<%} else if(request.getAttribute("page").equals("addTeams")){%>		
						<%@include file="/JSP/team/addTeams.jsp"%>
		<%} else if(request.getAttribute("page").equals("fixture")){%>
						<%@include file="/JSP/tournament/fixture.jsp"%>
		<%} else if(request.getAttribute("page").equals("position")){%>
						<%@include file="/JSP/tournament/positionTable.jsp"%>
		<%} else if(request.getAttribute("page").equals("results")){%>
						<%@include file="/JSP/tournament/tournamentResults.jsp" %>
		<%}%>
			


		</div>
			<div class="footer">
				<%@include file="/JSP/public/footer.jsp" %>
				<br clear="all" /></div>	

		</div>
	
	</body>
</html>

