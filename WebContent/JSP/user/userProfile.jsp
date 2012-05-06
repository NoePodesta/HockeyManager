
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Profile</title>

<%@include file="/JSP/public/js_includes.jsp" %>
<%@include file="/JSP/public/css_includes.jsp" %>
</head>
<body>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
		<div class="wrapper">
			<%@include file="/JSP/public/header.jsp" %>
							<div class="content">
	<div class="profile">
	<h1>Perfil</h1>
	
	<div class="bio">
	<div class= "img">
		<img class="userImg" src=ImageController  width="300" height="300" alt="User Photo"/>

	</div>
	<div class= "summary">
		<% String username = (String)request.getAttribute("userName"); %>
		<div class="group"><label>Nombre de Usuario: </label><%out.println(username);%></div>
	
		<div class="group"><label>Nombre: </label><%out.println(request.getAttribute("name"));%></div>
		
		<div class="group"><label>Apellido: </label><%out.println(request.getAttribute("lastname"));%></div>
		
		<div class="group"><label>Email: </label><%out.println(request.getAttribute("email"));%></div>
	</div>
	</div>

	
	<div class="pull-right">
		<form method="post" action="UserManager">
			<input type="hidden" name="action" value="DELETEUSER" />
			<button class="btn-primary btn-small" type="submit">Eliminar</button>
		</form>
	</div>
	<div class="pull-right">
		<form method="post" action="UserManager">
			<input type="hidden" name="action" value="MODIFYUSERPAGE" />
			<button class="btn-primary btn-small" type="submit">Modificar</button>
		</form>				
	</div>

</div>
		

		</div>
			<div class="footer">
				<%@include file="/JSP/public/footer.jsp" %>
				<br clear="all" /></div>	

		</div>

		
	
	</body>
</html>


