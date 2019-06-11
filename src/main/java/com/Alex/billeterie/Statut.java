package com.Alex.billeterie;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
class Statut {
	
	//On met l'id en auto_increment grace a generatedValue

  private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id_Statut;
  private String nom_Statut;
  private int pourcent_place;

  Statut() {}

  Statut(String nom_Statut, int pourcent_place) {
    this.nom_Statut = nom_Statut;
    this.pourcent_place = pourcent_place;
  }
}