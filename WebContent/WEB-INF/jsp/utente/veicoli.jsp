<%@page import="model.Veicoli"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<div class="jumbotron">
  <h1 class="display-4">Scegli la macchina</h1>
  <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
  <hr class="my-4">
  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
 <div class="container-fluid">
 <div class="row">
 <% ArrayList<Veicoli> veicoli = (ArrayList<Veicoli>)session.getAttribute("veicoli");
		  for(Veicoli veicolo:veicoli){ 
 %>
 <div class="card mr-5" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title"> <%=veicolo.getModello()%></h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    <form action="/RentalCar/utente/reviewNoleggio" method="POST">
    <input hidden name="email" value=<%=session.getAttribute("email")%>>
    <input hidden name="targa" value=<%=veicolo.getTarga()%>>
    <button type="submit" class="btn btn-primary">Prenota</a>
    </form>
  </div>
</div>
<%  } %>
 </div>
 </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>