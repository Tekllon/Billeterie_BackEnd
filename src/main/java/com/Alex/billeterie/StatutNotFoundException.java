package com.Alex.billeterie;

public class StatutNotFoundException extends RuntimeException {

	StatutNotFoundException(Long id) {
	    super("Could not find statut " + id);
	  }
	}
