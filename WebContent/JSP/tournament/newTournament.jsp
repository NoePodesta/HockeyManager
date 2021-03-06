<div id="newTournament">

    <div class="title">Registrar Torneo</div>

	<form id="myform" method="post" action="TournamentManager?action=REGISTERTOURNAMENT" enctype="multipart/form-data">

		<div>
			<label>Nombre del Torneo<span>&#32;*&#32;</span>:</label>
            <input id= "tournamentName" name="tournamentName" type="text" title="Debe ser de al menos 3 caracters, no se tomaran en cuenta
            las mayúsculas" pattern="[a-zA-Z ]{3,}" required="required" minlength="3" value="" placeholder="Nombre del Torneo"/>
            <span class="status"></span>
        </div>
        <div class="counter">
            <label>Descripción del Torneo<span>&#32;*&#32;</span>:</label>
            <textarea name="description" id="description" cols="50" rows="4" required="required" placeholder= "Descripción del Torneo"></textarea>
        </div>

            <label>Imagen:&#32;</label>
            <input type="file" name="file" id="file" accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>
            <output id="list"></output>



        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>
	</form>

</div>

<script type="text/javascript">
    $(document).ready(function(){
        arg.countCharacters();
        arg.thumbnailImage();
        arg.checkTournamentName();
    });
</script>
