<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<% String errore = (String)request.getAttribute("errore"); %>
	<% String param = (String)request.getAttribute("cat"); %>
	<div class="jumbotron">
		<h1 class="display-4">
			Aggiungi Veicolo</h1>
		<p class="lead">This is a simple hero unit, a simple
			jumbotron-style component for calling extra attention to featured
			content or information.</p>
		<hr class="my-4">
		<%if(errore != null && errore.equals("1")){%> <div class="badge badge-danger text-wrap">Valori non validi </div><%}%>
		<%if(errore != null && errore.equals("2")){%> <div class="badge badge-warning text-wrap" >Valori vuoti </div><%}%>
		<%if(errore != null && errore.equals("3")){%> <div class="badge badge-warning text-wrap">Valori gia presenti nel db </div><%}%>
		<div class="container-fluid">
		<div class="col">
		<form action="addVeicolo" method="POST">
			<div class="form-group">
			<label>Targa</label> <input	type="text" class="form-control" name="regTarga">
			</div>
			<div class="form-group">
			<label>Categoria</label> 
			<input type="text" disabled class="form-control" placeholder="<%=param%>" value="<%=param%>">
			<input type="text" hidden class="form-control" name="regCategoria" placeholder="<%=param%>" value="<%=param%>">
			</div>
			<div class="form-group">
			<label>Marca</label> <input	type="text" class="form-control" name="regMarca">
			</div>
			<div class="form-group">
			<label>Modello</label> <input	type="text" class="form-control" name="regModello">
			</div>
			<div class="form-group">
			<label>NPosti</label> <input type="text" class="form-control" name="regNPosti">
			</div>
			<div class="form-group">
			<label>Capacità bagaglio</label> <input type="text" class="form-control" name="regCapBag">
			</div>
			<div class="form-group">
			<label>Colore</label> <input type="text" class="form-control" name="regColore">
			</div>
			<button type="submit" class="btn btn-primary">Fatto!</button>
		</form>
		</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>


