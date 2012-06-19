<%@page import="model.Tournament"%>
<%Tournament tournament = (Tournament) request.getAttribute("tournament");%>
<form id="myform" method="post" action="TournamentManager">
	<input type="hidden" name="action" value="MODIFYTOURNAMENT"/>
		<p><label>Breve descripcion de su torneo <span>*</span></label> 
		<div class="counter"> 
			<textarea name="description" id="description" cols= "50" rows= "5" required="required"><%=tournament.getDescription()%></textarea>
		</div></p>	
		<div class="displayRight">
			<button type="submit"> Modificar Torneo </button>
		</div>
</form>

<script type="text/javascript">
	$(document).ready(function(){
		arg.countCharacters();
	});
</script>
		