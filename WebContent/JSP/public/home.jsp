<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<%@include file="js_includes.jsp"%>
<%@include file="css_includes.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@ include file="/JSP/public/header.jsp"%>
		<div class="content">


	<%-- 		<%@ include file="/JSP/comment/Comment.jsp"%>
 --%>		</div>


	
	<div class="footer">
		<%@include file="/JSP/public/footer.jsp"%>
	</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			arg.dialogs();
			arg.signin();
			arg.signup();
			arg.linkDisplay();
			//arg.checkUserName();
			//arg.autocomplete();
			arg.comment();

		});
	</script>

</body>
</html>
