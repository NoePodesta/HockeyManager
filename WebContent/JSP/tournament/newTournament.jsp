<div class="newTournament">
	<form id="myform" method="post" action="TournamentManager">
		<input type="hidden" name="action" value="REGISTERTOURNAMENT" />


		<p>
			<label>Nombre del Torneo <span>*</span></label> <input
				name="tournamentname" type="text"
				title="Debe ser de al menos 3 caracters, no se tomaran en cuenta las mayúsculas"
				pattern="[a-zA-Z ]{3,}" required="required" minlength="3" value="" />
		</p>

		<p>
			<label>Breve descripcion de su torneo <span>*</span></label>
		<div class="counter">
			<textarea name="description" id="description" cols="50" rows="5"
				required="required"> </textarea>
			</p>
		</div>
		<div class="displayRight">
			<button type="submit">Crear Torneo</button>
		</div>
	</form>
</div>
