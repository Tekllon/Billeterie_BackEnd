package com.Alex.billeterie;

import org.springframework.data.jpa.repository.JpaRepository;

//Utilisation du repositoryJpa pour pouvoir organiser les données relationnelles
interface PlaceRepository extends JpaRepository<Place, Long> {

}
