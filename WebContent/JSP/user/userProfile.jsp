
<div id="profile">
	<div class="title">Perfil</div>

	<div class="bio">
		<div class= "img">
			<img class="userImg" src=ImageController?action=USER  alt="User Photo"/>
			
		</div>
		<div class= "summary">
			<% String username = (String)request.getAttribute("userName"); %>
			<div class="group"><label>Nombre de Usuario: </label><%out.println(username);%></div>
			<div class="group"><label>Nombre: </label><%out.println(request.getAttribute("name"));%></div>
			<div class="group"><label>Apellido: </label><%out.println(request.getAttribute("lastname"));%></div>
			<div class="group"><label>Email: </label><%out.println(request.getAttribute("email"));%></div>
		</div>
	</div>
	<div class= buttons>
		<div class="pull-right">
			<form method="post" action="UserManager">
				<input type="hidden" name="action" value="DELETEUSER" />
				<button class="btn-primary btn-small" type="submit">Eliminar</button>
			</form>
		</div>
		<div class="pull-right">
			<a href="UserManager?action=MODIFYUSERPAGE" rel="#overlay" style="text-decoration:none">
				<button class="btn-primary btn-small" type="button">Modificar</button>
			</a>
		</div>
	</div>

</div>


<div class="apple_overlay" id="overlay">
	<!-- the external content is loaded inside this tag -->
	<div class="contentWrap"></div>
</div>


