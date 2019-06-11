package com.Alex.billeterie;

//Utilisation du repositoryJpa pour pouvoir organiser les données relationnelles

public class StatutNotFoundException extends RuntimeException {

	StatutNotFoundException(Long id) {
	    super("Could not find statut " + id);
	  }
	}
