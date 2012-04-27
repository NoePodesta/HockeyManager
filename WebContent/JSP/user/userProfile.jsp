<div class="profile">	
	<% String username = (String)request.getAttribute("userName"); %>
	<p><label>Nombre de Usuario: </label><%out.println(username);%></p>

	<p><label>Nombre: </label><%out.println(request.getAttribute("name"));%></p> 
	
	<p><label>Apellido: </label><%out.println(request.getAttribute("lastname"));%></p>
	
	<p><label>Email: </label><%out.println(request.getAttribute("email"));%></p>
	
	<p><label>Photo: </label> <img class="userImg" src=ImageController?action=USERIMAGE alt=""/>
</p>

	
	
	<div class="displayRight">
		<form method="post" action="UserManager">
			<input type="hidden" name="action" value="DELETEUSER" />
			<button type="submit">Eliminar</button>
		</form>
		<form method="post" action="UserManager">
			<input type="hidden" name="action" value="MODIFYUSERPAGE" />
			<button type="submit">Modificar</button>
		</form>				
	</div>

</div>