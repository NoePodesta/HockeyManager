<fieldset id="signup_menu">
	<p>Todos los campos con <span>*</span> deben ser completados.</p>
	 <br/>	 
	 <form id="signup" name="myform" action="UserManager?action=ADDUSER" enctype="multipart/form-data" method="POST">
			
			<label>Tipo de Usuario <span>*&nbsp;&nbsp;&nbsp;</span>
			<input class="checkbox" type="radio" name="tipo" value="1" title= "Usted tendra la posibilidad de crear y manejar torneos"/> User Admin
			&nbsp;
			<input class="checkbox" type="radio" name="tipo" value="2" title= "Usted tendra la posibilidad de comentar los partidos" CHECKED />User </label>
			<p class="help-block"><strong>Note: </strong>Si usted se registra como User Admin tendra la posibilidad de crear torneos, de lo contrario
			 solo podra comentar los partidos y ser parte de esta gran comunidad.</p>
			
			<input id="userId" name="userId" type="text" required="required" autocomplete="off" placeholder="Nombre de Usuario"/>
			<span class="status"></span>	
			
			<input name="password" id="p1"
				type="password" placeholder="Contraseña" title= "Trate que sea una contraseña dificil de adivinar" required="required" min="3"  />
			<input name="confirmpassword" type="password" id="p2" title= "Debe concordar con la contraseña" required="required"
				placeholder="Confirmación de contraseña" onfocus="validatePass(document.getElementById('p1'), this);" oninput="validatePass(document.getElementById('p1'), this);"/>
	
			<input name="name" type="text" placeholder="Nombre" title= "Debe ser de entre 3 y 20 caractéres" required="required" max="20" min="3" value="" />

			<input name="lastname" type="text" placeholder="Apellido" title= "Debe ser de entre 3 y 20 caractéres" required="required"  max="20" min="3" value="" />
	
			<input name="email" type="email"  placeholder="Email" title= "Su mail no será publicado a los demás usuarios" required="required" value="" />
			
			<label>Foto</label>
			<input type="file" lang="es" name="file" id="file" style="margin-top: 5px;" accept="image/gif,image/jpeg, image/png, image/jpg" size=30/></p>
   			<output id="list"></output>
	
			
			<div class="pull-right">
				<button class="btn-primary btn-small" type="submit"> OK </button>
			</div>
		</form>
</fieldset>

<script>
    $(document).ready(function(){
        arg.countCharacters();
        arg.thumbnailImage();
        arg.checkUserName();
        arg.fileStyle();
    });
</script>