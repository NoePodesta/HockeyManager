<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div class="addResult">
    <h1>Resultado</h1>


    <% String localteam = (String)request.getAttribute("localteam");
			String guestteam= (String)request.getAttribute("guestteam");
			int idmatch = (Integer) request.getAttribute("idmatch");
					
		%>
		<form id="myform" method="post" action="MatchManager?idmatch=<%=idmatch%>">
		<input type="hidden" name="action" value="ADDRESULT"/>
			<p><label><%=localteam%></label> 
			<input name="localresult" type="number" required="required" value="" />
			<label><span>VS</span></label>
			<input name="guestresult" type="number" required="required" value="" />
			<label><%=guestteam%></label></p> 
				
		
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>	
					
		</form>
</div>