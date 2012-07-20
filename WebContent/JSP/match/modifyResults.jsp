<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div id="matchResult">

    <div class="title">Resultado</div>

    <% int idMatch = (Integer) request.getAttribute("idMatch");%>
		<form id="myform" method="post" action="MatchManager">
		<input type="hidden" name="action" value="MODIFYRESULT"/>
        <input type="hidden" name="idMatch" value="<%=idMatch%>"/>

            <div  style="padding-top:30px; padding-bottom: 40px;">
                <label>${localteam }</label>
                <input name="localresult" type="number"  min="0" required="required" value=${localresult } />
                <label><span>VS</span></label>
                <input name="guestresult" type="number"  min="0" required="required" value=${guestresult } />
                <label>${guestteam }</label>
            </div>

            <div class="pull-right">
                <button class="btn-primary btn-small" type="submit"> OK</button>
            </div>
					
		</form>
</div>
