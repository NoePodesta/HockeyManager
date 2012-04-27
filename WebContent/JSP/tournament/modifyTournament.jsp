<div class="titulo"> 
	<h1>Modificar Torneo</h1>
	<h3 style="margin-bottom: 0px; margin-top: 0px;" >${tournamentname }</h3>
</div>
<form id="myform" method="post" action="TournamentManager" onsubmit="return validate_form(this)">
	<input type="hidden" name="action" value="MODIFYTOURNAMENT"/>
		<p><label>Breve descripcion de su torneo <span>*</span></label> 
		<div class="counter"> 
			<textarea name="description" id="description" cols= "50" rows= "5" required="required">${description }</textarea>
		</div></p>	
		<div class="displayRight">
			<button type="submit"> Modificar Torneo </button>
		</div>
</form>

<script type="text/javascript">
	$(document).ready(function(){
		arg.countCharacters();
		arg.validation();

																				
	});
</script>
		