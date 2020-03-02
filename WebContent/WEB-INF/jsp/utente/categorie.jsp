<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Categorie"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title><%=session.getAttribute("email")%></title>
</head>
<body>
	<div class="jumbotron">
		<h1 class="display-4">
			Benvenuto
			<%=session.getAttribute("email")%></h1>
		<p class="lead">In questa pagina potrai scegliere le fantasiche categorie che abbiamo a disposizione</p>
		<hr class="my-4">
		<p>Seleziona la categoria che ti piu aggrada
		<small class="form-text text-muted">Puoi visualizzare i prezzi dopo aver scelto la macchina e il periodo di utilizzo</small></p>
		<div class="container-fluid">
			<div class="row">
				<%
					ArrayList<Categorie> categorie = (ArrayList<Categorie>) session.getAttribute("categorie");
					for (Categorie categoria : categorie) {
				%>
				<div class="card mr-5" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">
							<%=categoria.getNomeCategoria()%></h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<form action="/RentalCar/utente/veicoliNoleggio" method="POST">
							<input hidden name="email"
								value=<%=session.getAttribute("email")%>>
							<input hidden name="categoria"
								value=<%=categoria.getIdCategoria()%>>
							<button type="submit" class="btn btn-primary">
								Vedi le auto</a>
						</form>
					</div>
				</div>
				<%
					}
				%>
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