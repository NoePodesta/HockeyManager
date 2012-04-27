<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div class="addResult">
	<h1>Resultado</h1>
	<%@page import="model.Team"%>
	
	
		<% int idmatch = (Integer) request.getAttribute("idmatch");
		
				
		%>
		<form id="myform" method="post" action="MatchManager?idmatch=<%=idmatch%>">
		<input type="hidden" name="action" value="MODIFYRESULT"/>
			<p><label>${localteam }</label> 
			<input name="localresult" type="number" required="required" value=${localresult } />
			<label><span>VS</span></label>
			<input name="guestresult" type="number" required="required" value=${guestresult } />
			<label>${guestteam }</label></p> 
				
		
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>	
					
		</form>
</div>