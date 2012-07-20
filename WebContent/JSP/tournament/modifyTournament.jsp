<%@page import="model.Tournament" %>
<%Tournament tournament = (Tournament) request.getAttribute("tournament");%>
<div id="modifyTournament">

    <div class="title">Modificar <%=tournament.getName()%></div>
    <form id="myform" method="post" action="TournamentManager?action=MODIFYTOURNAMENT" enctype="multipart/form-data">

        <div class="counter">
            <label>Descripci√≥n del Torneo<span>&#32;*&#32;</span>:</label>
            <textarea name="description" id="description" cols="50" rows="4"
                      required="required"><%=tournament.getDescription()%>
            </textarea>
        </div>
        <div><label>Imagen Actual:&#32;</label>
        <img class="image" src=ImageController?action=TOURNAMENT&value=<%=tournament.getIdTournament()%>&size=80
             alt="Tournament Image"/>
        </div>
        <div><label>Nueva Imagen:&#32;</label>
        <input type="file" name="file" id="file"
               accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>
        <output id="list"></output>
        </div>
        <p>Si la imagen no se cambia, permanecer· la actual. </p>

        <div class="pull-right" style="margin-top: -5px;">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        arg.countCharacters();
        arg.thumbnailImage();
    });
</script>
		