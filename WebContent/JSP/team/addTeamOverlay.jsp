<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div id="addTeamOverlay">

    <div class="title">Agregar Equipo</div>

    <p>Todos los campos con <span>*</span> deben ser completados.</p>

    <form id="myform" name="myform" method='post' action="TeamManager?action=ADDTEAM" enctype="multipart/form-data">

        <div>
            <label>Nombre del Equipo:<span>&#32;*&#32;:</span></label>
            <input id="teamName" name="teamName" type="text" required="required" minlength="3" value=""
                   placeholder="Nombre del Equipo"/></p>
            <span class="status"></span>	
        </div>
        <div class="counter">
            <label>Descripción del Equipo<span>&#32;*&#32;:</span></label>
            <textarea name="description" id="description" cols="50" rows="4" required="required"></textarea></p>
        </div>
        <div>
            <label>Imagen:&#32;</label>

        </div>
        <input type="file" name="file" id="file" accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>

        <output id="list"></output>

        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>

    </form>

    <script type="text/javascript">
        $(document).ready(function () {
            arg.countCharacters();
            arg.checkTeamName();

        });

    </script>

</div>