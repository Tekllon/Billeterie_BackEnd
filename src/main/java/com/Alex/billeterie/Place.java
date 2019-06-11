package com.Alex.billeterie;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
class Place {

  private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id_place;
  private String nom_place;
  private float prix_final;

  Place() {}

  Place(String nom_place, float prix_final) {
    this.nom_place = nom_place;
    this.prix_final = prix_final;
  }
}