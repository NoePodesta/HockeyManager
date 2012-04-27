<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<div class="addDate">
	<h1>Día del Partido</h1>
	
		<%String idmatch = (String)request.getParameter("idmatch"); 
		System.out.println(idmatch);%>	
		<form id="myform" method="post" action="MatchManager?idmatch=<%=idmatch%>">
		<input type="hidden" name="action" value="ADDDATE"/>
			<p><label>Fecha <span>*</span></label> 
			<input type="date" title="Ingrese la fecha para que se dispute el partido"
					required="required" name="date" readonly/></p>
		
			<div class="displayRight">
				<button type="submit"> OK </button>
			</div>	
					
		</form>
		
			<script type="text/javascript">
			$(document).ready(function(){
				arg.date();
																											
			});
			</script>
</div>