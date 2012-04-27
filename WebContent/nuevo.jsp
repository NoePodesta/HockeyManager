<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Hockey Site </title>
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="tournamentStyle.css" rel="stylesheet" type="text/css" />
<script src="JS/jquery.tools.min.js"></script>
<script src="JS/functions.js"></script>

</head>
<body id="page7">
<table width="100%" cellpadding="0">
	<tr>
		<td class="site_center">
		<table cellpadding="0" class="site_center4">
			<tr>
				<td class="col_1">
				<div class="indent">
				<ul class="tabs">
				<li class="w3"><b><a href="#1"> Torneo</a></b></li>
				<li class="w3"><b><a href="#2"> Fixture</a></b></li>
				<li class="w3"><b><a href="#3"> Resultados</a></b></li>
				<li class="w3"><b><a href="#4"> Tabla de Posiciones</a></b></li>
				<li class="w3"><b><a href="#5"> Goleadoras</a></b></li>
				</ul>
			<div id = tournament class="panes">
			<div id=1>
			<h2>hola</h2>
			<p>jndtglg</p>
			</div>
			<div id=2>
			<h2>Fixture</h2>
			
 					<%@include file="/JSP/public/login.jsp" %>
			</div>
			<div id=3>
			<h2>Resultados</h2>
			<table cellpadding="5" id="fixtureview" class="site_center4">
 					
				<tr>
					<th>FECHA</th>
					<th>DIA</th>
					<th>EQUIPO VISITANTE</th>
					<th></th>
					<th>RESULTADO</th>
					<th></th>
					<th>EQUIPO LOCAL</th>
				</tr>
				
					<tr>
						<td><label>fecha</label></td>
						<td><label>
					
						No hay fecha asignada
				
						</label></td>						
						<td><label>team</label></td>
						<td><label>
						
						</label></td>
						<td><label>VS</label>
						
						<td><label>
						
						</label></td>
						<td><label></label></td>			
						</tr>
					
 					</table>
			</div>
			<div id=3>
			<h2>tabla de posiciones</h2>
				<table cellpadding="5" id="fixtureview" class="site_center4">
					<tr>
						<th>EQUIPO</th>
						<th>PUNTOS</th>
						<th>PJ</th>
						<th>PG</th>
						<th>PE</th>
						<th>PP</th>
						<th>GF</th>
						<th>GC</th>
						<th>DIF</th>
					</tr>
				
					<tr>
					
						<td><label>teamname</a></label></td>
						<td><label></label></td>
						<td><label></label></td>
						<td><label></label></td>
						<td><label></label></td>
						<td><label></label></td>
						<td><label></label></td>
						<td><label></label></td>
						<td><label></label></td>
					</tr>
					
					</table>
				<p>PJ= partidos jugados, PG= Partidos ganados, PE= Partidos
				empatados, PP= Partidos perdidos, GF= Goles a favor, GC= Goles en contra, </p> 
				<p>DIF= Diferencia entre goles en contra y a favor.</p>
				</div>
			<div id=4>
			<h2>goleadoras</h2>
		
			<table cellpadding="5" id="fixtureview" class="site_center4">

					<tr>
						<th>JUGADORA</th>
						<th>GOLES</th>
					</tr>
		
				<tr>
						<td><label>nuevo</label></td>
						<td><label>gles</label></td>
						</tr>
				 			
					</table>
			</div>
			</div>
			</div>
			<div class="apple_overlay" id="overlay">
				<div class="contentWrap"></div>
				</div>
			</td>
			</tr>
		
	<tr>
		<td id="footer">
				<div class="indent">HockeySite &copy; 2010 &bull; <a
					href="index.jsp">Home</a></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<script>
$(document).ready(function(){
	arg.tabs();

																			
});
</script>

</script>

</body>
</html>
