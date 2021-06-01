<%@page import='moteur_police.*' %>
<%@ page language="java" import='java.util.*' contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    </head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">N7 Investigation</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link " aria-current="page" href="index.html">Ajout</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="recherche.html">Recherche</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="ajouter_preuves">Preuves</a>
          </li>
          <li class="nav-item log-out">
            <a class="nav-link" href="login.html" tabindex="-1" >Log out</a>
          </li>
        </ul>
      </div>
    </div>
    </nav>
	<div class="d-flex h-100  align-items-center ">
        <div class="bloc1 d-flex flex-column align-center">
        	<div class="d-flex p-2 bd-highlight justify-content-center">
            	<div class="mb-3 col-3">
					<p> Voici le dossier de l'affaire : <p>

					<% Affaire affaire = (Affaire)request.getAttribute("affaire"); %>

   					 <p>Nom : <%= affaire.getNom() %> 
   					 	Complices : <% for (Identite suspect : affaire.getSuspects()) { %> <a href="Servlet?op=valider_recherche&numero=<%= suspect.getNumero() %>"> <%= suspect.getNom() %> <%= suspect.getPrenom() %> </a>  <% } %> <p>

					<a href="index.html"> Revenir à l'accueil </a>
				</div>
			</div>
		</div>
	</div>
</body>


</html>