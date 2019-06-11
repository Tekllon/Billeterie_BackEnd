package com.Alex.billeterie;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

//Utilisation du repositoryJpa pour pouvoir organiser les données relationnelles

interface TribuneRepository extends JpaRepository<Tribune, Long> {


}
