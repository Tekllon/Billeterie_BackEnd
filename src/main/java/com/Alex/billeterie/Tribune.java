package com.Alex.billeterie;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity

class Tribune {

private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;
  private String nom_trib;
  private float prix_base;

  Tribune() {}

  Tribune(String nom_trib, float prix_base) {
    this.nom_trib = nom_trib;
    this.prix_base = prix_base;
  }
}