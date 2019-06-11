package com.Alex.billeterie;

public class TribuneNotFoundException extends RuntimeException {

	TribuneNotFoundException(Long id) {
	    super("Could not find tribune " + id);
	  }
	}

