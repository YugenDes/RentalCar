<%@page import="model.Veicoli"%>
<%@page import="model.Noleggi" %>
<%@page import="java.util.ArrayList" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<% String  email = (String)session.getAttribute("email"); %>
	<div class="jumbotron">
		<h1 class="display-4">
			Gestione Prenotazione </h1>
		<p class="lead">This is a simple hero unit, a simple
			jumbotron-style component for calling extra attention to featured
			content or information.</p>
		<hr class="my-4">
		<%if(errore != null && errore.equals("1")){%> <div class="badge badge-danger text-wrap">Valori non validi </div><%}%>
		<%if(errore != null && errore.equals("2")){%> <div class="badge badge-warning text-wrap" >Valori vuoti </div><%}%>
		<%if(errore != null && errore.equals("3")){%> <div class="badge badge-warning text-wrap">Tempo di pren < 10min </div><%}%>
		<%if(errore != null && errore.equals("4")){%> <div class="badge badge-warning text-wrap">Data di nascita invalida minore 18 oppure maggiore 65 </div><%}%>
		<div class="container-fluid">
		<div class="col">
			<form action="/RentalCar/utente/checkNoleggio" method="POST">
    </div>   
    </div>
    <div class="row">
        <div class="col">
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label>Data Inizio</label>
                        <input type="date" class="form-control" name="dataInizio">
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Orario Inizio</label>
                        <input type="time" class="form-control" name="oraInizio">
                    </div>
                </div>
            </div>
        </div>
       </div>
       <div class="row">
        <div class="col">
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label>Data Fine</label>
                        <input type="date" class="form-control" name="dataFine">
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Orario Fine</label>
                        <input type="time" class="form-control" name="oraFine">
                    </div>
                </div>
            </div>
        </div>
       </div>
    <button type="submit" class="btn btn-primary">Fatto!</button>
</form>
		</div>
		</div>
		<div class="jumbotron">
		<h1 class="display-4">
			Prenotazioni correnti</h1>
		<hr class="my-4">
		 <div class="row ml-5">
 <% ArrayList<Noleggi> noleggi = (ArrayList<Noleggi>)session.getAttribute("noleggiEff");
 if(noleggi!=null){
		  for(Noleggi noleggio:noleggi){ 
 %>
 <div class="card mr-5 mb-5" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title"> <%=noleggio.getVeicoli().getModello()%></h5>
    <p class="card-text">Data inizio : <%=noleggio.getInizioNoleggio()%></p>
    <p class="card-text">Data fine : <%=noleggio.getFineNoleggio()%></p>
    <p class="card-text">Importo : <%=noleggio.getImportoDovuto() %></p>
  </div>
</div>
<%  }} %>
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


