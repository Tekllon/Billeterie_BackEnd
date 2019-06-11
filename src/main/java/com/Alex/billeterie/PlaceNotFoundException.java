package com.Alex.billeterie;

public class PlaceNotFoundException extends RuntimeException {

	PlaceNotFoundException(Long id_place) {
	    super("Could not find place " + id_place);
	  }
	}

