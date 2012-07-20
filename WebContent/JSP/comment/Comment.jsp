<link href="CSS/Style.css" rel="stylesheet" type="text/css" />
<script src="JS/jquery.tools.min.js" type="text/javascript"></script>
<script src="JS/functions.js" type="text/javascript"></script>

<div id="teamProfile">

	<h1>Comentarios</h1>
			
		<%@page import="model.Comment"%>
		<%@page import="model.User"%>
		<%@page import="java.util.List"%>

<div id="commentContainer">
    <% List<Comment> comments = (List<Comment>) request.getAttribute("comments");
		
		for (int i = 0; i < comments.size(); i++) {
		 	String date = comments.get(i).getDate();
		 	User user = comments.get(i).getUser();
		 	String comment = comments.get(i).getCommemt();
		 	%>
			<div class="comment">
 			<div><img class="avatar" src="ImageController?action=USER&value=<%=user.getUserName()%>&size=25" alt="Team Image" /></div>
 		    <div class="name"><%=user.getName()%></div>
			<div class="date"><%=date%>></div>
			<p><%=comment%></p>
			</div>
			<%}%>
		</div>
		
	
	
	<div id="flash"></div>
	
    <%if(!request.getRemoteUser().isEmpty()){%>
		<form id="submitComment" method="post" action="Comment">
		<div class="counter">
			<textarea name="comment" id="comment" cols="50" rows="5"> </textarea>
		</div>
            <div class="submitComment pull-right">
                <button class="btn-primary btn-small" type="submit"> OK</button>
            </div>
					
		</form>
    <%}%>

</div>
			<script type="text/javascript">
		$(document).ready(function() {
			arg.comment();

		});
	</script>
		
		
		
		