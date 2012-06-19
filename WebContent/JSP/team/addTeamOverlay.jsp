<div id="addTeamOverlay">

    <div class="title">Agregar Equipo</div>

    <p>Todos los campos con <span>*</span> deben ser completados.</p>

    <form id="myform" name="myform" method='post' action="TeamManager">
        <input type="hidden" name="action" value="ADDTEAM"/>

        <div>
            <label>Nombre del Equipo:<span>&#32;*&#32;:</span></label>
            <input name="teamname" type="text" required="required" minlength="3" value=""
                   placeholder="Nombre del Equipo"/></p>
        </div>
        <div class="counter">
            <label>Descripción del Equipo<span>&#32;*&#32;:</span></label>
            <textarea name="description" id="description" cols="50" rows="4" required="required"></textarea></p>
        </div>
        <div>
            <label>Imagen:&#32;</label>

        </div>
        <input type="file" name="file" id="file" style="margin-left: -47px;" onchange="getPath(this)"  accept="image/gif,image/jpeg, image/png, image/jpg" size=30/>

        <output id="list"></output>

        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>

    </form>

    <script type="text/javascript">
        $(document).ready(function () {
            arg.countCharacters();

        });

        $("input[type=file]").filestyle({
            image: 'image/chooser.gif',
            imageheight : 22,
            imagewidth : 82,
            width : 250
        });

        if (window.FileReader) {
            function handleFileSelect(evt) {
                var files = evt.target.files;
                var f = files[0];
                var reader = new FileReader();

                reader.onload = (function(theFile) {
                    return function(e) {
                        document.getElementById('list').innerHTML = ['<img src="', e.target.result,'" title="', theFile.name, '" width="150"/>'].join('');
                    };
                })(f);

                reader.readAsDataURL(f);
            }
        } else {
            alert('This browser does not support FileReader');
        }

        document.getElementById('file').addEventListener('change', handleFileSelect, false);

    </script>

</div>