<div class="addTeamOverlay">
		
	<div class="titulo"> 
		<h1>Agregar Equipo</h1>
	</div>

	
	<p>Todos los campos con <span>*</span> deben ser completados.</p>
	 
	 	<form id="myform" name="myform" method='post' action="TeamManager">
			<input type="hidden" name="action" value="ADDTEAM"/>
				
			<p><label>Nombre del equipo <span>*</span></label> 
			<input name="teamname" type="text" required="required" minlength="3" value="" /></p>
			
			
			<div class="counter">
			<p><label>Descripción del Equipo<span>*</span></label> 
			<textarea name="description" id="description" cols= "50" rows= "4" required="required"></textarea></p>
			</div>	
			
			<div class="counter">
			<p><label>Historia del Equipo<span>*</span></label> 
			<textarea name="history" id="description" cols= "50" rows= "4" required="required"></textarea></p>
			</div>		
			
			<p><label>Agregar Imagen<br/> <input type="file" name="photo" style="margin-top: 5px;" />
			</label><br/></p>
					
	
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>
			
		</form>
		
		<script type="text/javascript">
						$(document).ready(function(){
							arg.countCharacters();
																										
						});
		</script>
</div>