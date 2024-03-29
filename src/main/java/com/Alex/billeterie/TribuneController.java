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
class TribuneController {

  private final TribuneRepository repository;

  TribuneController(TribuneRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  //Pour récuperer toutes les tribunes
  
  @GetMapping("/tribunes")
  Resources<Resource<Tribune>> all() {

	  List<Resource<Tribune>> tribunes = repository.findAll().stream()
	    .map(tribune -> new Resource<>(tribune,
	      linkTo(methodOn(TribuneController.class).one(tribune.getId())).withSelfRel(),
	      linkTo(methodOn(TribuneController.class).all()).withRel("tribunes")))
	    .collect(Collectors.toList());

	  return new Resources<>(tribunes,
	    linkTo(methodOn(TribuneController.class).all()).withSelfRel());
	}

  //Pour ajouter une tribune
  @PostMapping("/tribune")
  Tribune newTribune(@RequestBody Tribune newTribune) {
    return repository.save(newTribune);
  }

  // Single item

  //Pour récuperer une tribune définie
  @GetMapping("/tribune/{id}")
  Resource<Tribune> one(@PathVariable long id) {
	  
    Tribune tribune = repository.findById(id)
    		//Pour gérer les erreurs
      .orElseThrow(() -> new TribuneNotFoundException(id));
    
    //linkTo pour ajouter des liens dans le retour Json, et faciliter la navigation dans l'API
    return new Resource<>(tribune,
    	    linkTo(methodOn(TribuneController.class).one(id)).withSelfRel(),
    	    linkTo(methodOn(TribuneController.class).all()).withRel("tribune"));
   }

 //Pour modifier une tribune définie

@PutMapping("/tribune/{id}")
  Tribune replaceTribune(@RequestBody Tribune newTribune, @PathVariable Long id) {

    return repository.findById(id)
      .map(tribune -> {
    	  tribune.setNom_trib(newTribune.getNom_trib());
    	  tribune.setPrix_base(newTribune.getPrix_base());
        return repository.save(tribune);
      })
      .orElseGet(() -> {
        newTribune.setId(id);
        return repository.save(newTribune);
      });
  }

//Pour supprimer une tribune définie

  @DeleteMapping("/tribune/{id}")
  void deleteTribune(@PathVariable Long id) {
    repository.deleteById(id);
  }
}