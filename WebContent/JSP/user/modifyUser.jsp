<div class="titulo"> 
	<h1>Perfil</h1>
</div>


<form id="myform" name="myform" method="POST" action="UserManager?action=MODIFYUSER" enctype="multipart/form-data">

	<p><label>Nombre <span>*</span></label> 
	<input name="name" type="text" title= "Debe ser de entre 3 y 20 caractéres" required="required" maxlength="20" minlength="3" value=${name } /></p>
	
	<p><label>Apellido <span>*</span></label>
	<input name="lastname" type="text" title= "Debe ser de entre 3 y 20 caractéres" required="required"  maxlength="20" minlength="3" value=${lastname } /></p>
	
	<p><label>Email <span>*</span></label> 
	<input name="email" type="email" title= "Su mail no será publicado a los demás usuarios" required="required" value=${email } /></p>
	
	<p><label>Foto</label> 
	<input type="file" lang="es" name="photo" id="photo" style="margin-top: 5px;" /></p>
		
		<p><label>Contraseña <span>*</span></label> <input name="password"
		type="password" title= "Trate que sea una contraseña dificil de adivinar" required="required" minlength="4" value="" /></p>

		<p><label>Confirmar contraseña <span>*</span></label> 
		<input name="newpassword" type="password" title= "Debe concordar con la contraseña" required="required"
			dataequals="password" value="" /></p>
				
	<div class="pull-right">
		<button type="submit"> OK </button>		
	</div>

</form>

			
		
