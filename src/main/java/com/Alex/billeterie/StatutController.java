package com.Alex.billeterie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StatutController {

  private final StatutRepository repository;

  StatutController(StatutRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  // Pour recuperer tous les statuts disponibles dans la BDD
  @GetMapping("/statuts")
  Resources<Resource<Statut>> all() {

	  List<Resource<Statut>> statuts = repository.findAll().stream()
	    .map(statut -> new Resource<>(statut,
	      linkTo(methodOn(StatutController.class).one(statut.getId_Statut())).withSelfRel(),
	      linkTo(methodOn(StatutController.class).all()).withRel("statuts")))
	    .collect(Collectors.toList());

	  return new Resources<>(statuts,
	    linkTo(methodOn(StatutController.class).all()).withSelfRel());
	}

  // Pour ajouter un statut, si besoin.
  @PostMapping("/statut")
  Statut newStatut(@RequestBody Statut newStatut) {
    return repository.save(newStatut);
  }

  // Single item

  //Pour récuperer un statut particulier
  @GetMapping("/statut/{id_statut}")
  Resource<Statut> one(@PathVariable long id_statut) {

    Statut statut = repository.findById(id_statut)
      .orElseThrow(() -> new StatutNotFoundException(id_statut));
    
    return new Resource<>(statut,
    	    linkTo(methodOn(StatutController.class).one(id_statut)).withSelfRel(),
    	    linkTo(methodOn(StatutController.class).all()).withRel("statut"));
   }

 //Pour modifier un statut, si besoin

@PutMapping("/statut/{id_statut}")
  Statut replaceStatut(@RequestBody Statut newStatut, @PathVariable Long id_statut) {

    return repository.findById(id_statut)
      .map(statut -> {
    	  statut.setNom_Statut(newStatut.getNom_Statut());
    	  statut.setPourcent_place(newStatut.getPourcent_place());
        return repository.save(statut);
      })
      .orElseGet(() -> {
        newStatut.setId_Statut(id_statut);
        return repository.save(newStatut);
      });
  }

//pour supprimer un statut, si besoin

  @DeleteMapping("/statut/{id_Statut}")
  void deleteStatut(@PathVariable Long id_Statut) {
    repository.deleteById(id_Statut);
  }
}