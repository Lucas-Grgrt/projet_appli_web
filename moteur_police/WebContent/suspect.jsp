<%@page import='moteur_police.*' %>
<%@ page language="java" import='java.util.*' contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rechercher fiches suspects</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="bloc4">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">N7 Investigation</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link " aria-current="page" href="accueil.html">Ajout</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="recherche.html">Recherche</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="ajouter_preuves">Preuves</a>
          </li>
          <li class="nav-item log-out">
            <a class="nav-link" href="index.html" tabindex="-1" >Log out</a>
          </li>
        </ul>
      </div>
    </div>
    </nav>
    <div class="bloc4 d-flex h-100  align-items-center ">
        <div class="bloc3 d-flex flex-column align-center">
            
           
            <div class="d-flex p-2 bd-highlight justify-content-center">
                <div class="mb-2 col-2">
                    <img src="classified.png" class="image" style="background:transparent">
                </div>
                <div class="mb-3 col-3">
                	<% Identite suspect = (Identite)request.getAttribute("suspect"); %>
                    <h1 class="display-3"> <%= suspect.getNom() %> </h1>
                    <h1 class="display-3"> <%= suspect.getPrenom() %>  </h1>
                </div>
                
                
                
                <div class="mb-3 offset-md-1 col-3">
                	<img src="Servlet?op=render_image&nom=<%=suspect.getNom()%>&prenom=<%=suspect.getPrenom()%>" class="img-thumbnail" alt="...">
                </div>
            </div>
            <div class="bloc_barre d-flex p-2 bd-highlight justify-content-center">
                <a class="btn btn-primary" href="accueil.html" role="button">Renseigner quelqu'un dans la base de données</a>
            </div>
        
        </div>
    </div>
</body>


</html>