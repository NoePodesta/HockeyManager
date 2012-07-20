<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div id="matchDate">
    <div class="title">Día del Partido</div>

    <%String idmatch = request.getParameter("idMatch");%>
    <form id="myform" method="post" action="MatchManager">
        <input type="hidden" name="action" value="ADDDATE"/>
        <input type="hidden" name="idMatch" value="<%=idmatch%>">

        <div style="padding-top:20px; padding-bottom: 20px;">
            <label>Fecha <span>*</span></label>

            <input type="date" title="Ingrese la fecha para que se dispute el partido"
                   required="required" name="date" readonly/></p>
        </div>

        <div class="pull-right">
            <button class="btn-primary btn-small" type="submit"> OK</button>
        </div>

    </form>

    <script type="text/javascript">
        $(document).ready(function () {
            arg.date();

        });
    </script>
</div>