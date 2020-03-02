<%@page import="java.util.Comparator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="model.Categorie"%>
<%@page import="model.Veicoli"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Utente" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
<!-- Session
	+)"utenti"
	+)"veicoli"
	+)"categorie"
 -->

<!-- Modal Veicolo -->
<div class="modal fade" id="veicoloModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Sicuro di voler eliminare il veicolo?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
        <button type="button" class="btn btn-primary">SI</button>
      </div>
    </div>
  </div>
</div>


<div class="jumbotron">
  <h1 class="display-4">Benvenuto <%=session.getAttribute("email")%></h1>
  <p class="lead"></p>
  <hr class="my-4">
  <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Email</th>
      <th scope="col">Password</th>
    </tr>
  </thead>
  <tbody>
    <% ArrayList<Utente> utenti = (ArrayList<Utente>)session.getAttribute("utenti");
		  int i=1;
		  if(utenti!=null){
		  for(Utente utente : utenti){
		  		if(utente==null) break;%>
			  <tr>
		      <th scope="row"><%=i++%></th>
		      <td><%=utente.getEmail()%></td>
		      <td><%=utente.getPassword()%></td>
		    </tr>
		  <% }}%>
  </tbody>
</table>

<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Email</th>
      <th scope="col">Giorni noleggi</th>
    </tr>
  </thead>
  <tbody>
    <% TreeMap<Integer, Integer> sorted = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
		public int compare(Integer int1, Integer int2) {
			return int2.compareTo(int1);
		}
			});
    	for(int j=0;j<utenti.size();j++){
    		if(utenti.get(j).giorniNoleggio()==0)continue;
    		sorted.put(j,utenti.get(j).giorniNoleggio());
    	}
		  for(Map.Entry<Integer, Integer> entry : sorted.entrySet()){ %>
			  <tr>
		      <th scope="row"></th>
		      <td><%=utenti.get(entry.getKey()).getEmail()%></td>
		      <td><%=entry.getValue()%></td>
		    </tr>
		  <% }%>
  </tbody>
</table>

 <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Targa</th>
      <th scope="col">Categoria</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <% ArrayList<Veicoli> veicoli = (ArrayList<Veicoli>)session.getAttribute("veicoli");
		if(veicoli!=null){  
    	for(Veicoli veicolo : veicoli){
		  		if(veicolo==null) break;%>
			  <tr>
		      <th scope="row"> </th>
		      <td><form action="modifyVeicolo" method="POST">
		      <input type="text" hidden  name="where" value="fromadmin">
		      <input type="text" hidden class="form-control" name="targa"value="<%=veicolo.getTarga()%>">
		      <button type="submit" class="btn btn-link"><%=veicolo.getTarga()%></button>
		       </form></td>
		      <td><%=veicolo.getCategorie().getIdCategoria()%></td>
		      <td><form action="removeVeicolo" method="POST">
		      <input type="text" hidden  name="targa" value="<%=veicolo.getTarga()%>">
		      <button disabled type=" button submit" class="ml-2 mb-1 close open-modal" data-dismiss="toast" aria-label="Close">
              <span aria-hidden="true">&times;</span></button>
     		 </form>
		      </td>
		    </tr>
		  <% }}%>
  </tbody>
</table>
<small class="form-text text-muted">Per aggiungere un veicolo cliccare sulla categoria desiderata</small>
  <div class="row">
<div class="mb-3 mt-3 mr-3 ml-3"><a class="btn btn-primary btn-lg" href="addCategoria" role="button">Aggiungi Categoria</a></div>
  </div>

<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nome</th>
      <th scope="col">Prezzo G</th>
      <th scope="col">Prezzo S</th>
      <th scope="col">Prezzo M</th>
    </tr>
  </thead>
  <tbody>
    <% ArrayList<Categorie> categorie = (ArrayList<Categorie>)session.getAttribute("categorie");
    	if(categorie!=null){
		  for(Categorie categoria : categorie){
		  		if(categoria==null) break;%>
			  <tr>
		      <th scope="row"><%=categoria.getIdCategoria()%></th>
		      <td><a href="addVeicolo?cat=<%=categoria.getNomeCategoria()%>"><%=categoria.getNomeCategoria()%></a></td>
		      <td><%=categoria.getTariffaGiornaliera()%></td>
		      <td><%=categoria.getTariffaSettimanale()%></td>
		      <td><%=categoria.getTariffaMensile()%></td>
		    </tr>
		  <% }}%>
  </tbody>
</table>

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