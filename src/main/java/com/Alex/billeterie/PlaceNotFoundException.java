package com.Alex.billeterie;

public class PlaceNotFoundException extends RuntimeException {

	//Message d'erreur a afficher en cas d'erreur
	
	PlaceNotFoundException(Long id_place) {
	    super("Could not find place " + id_place);
	  }
	}

