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
class PlaceController {

  private final PlaceRepository repository;

  PlaceController(PlaceRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  //Pour avoir un retour de toutes les places disponibles dans la BDD
  @GetMapping("/places")
  Resources<Resource<Place>> all() {

	  List<Resource<Place>> places = repository.findAll().stream()
	    .map(place -> new Resource<>(place,
	      linkTo(methodOn(PlaceController.class).one(place.getId_place())).withSelfRel(),
	      linkTo(methodOn(PlaceController.class).all()).withRel("places")))
	    .collect(Collectors.toList());

	  return new Resources<>(places,
	    linkTo(methodOn(PlaceController.class).all()).withSelfRel());
	}
  
  //Pour poster une place vers la bdd
  
  @PostMapping("/place")
  Place newPlace(@RequestBody Place newPlace) {
    return repository.save(newPlace);
  }

  // Single item

  //Pour trouver une place spécifique selon son ID
  
  @GetMapping("/place/{id_place}")
  Resource<Place> one(@PathVariable long id_place) {

    Place Place = repository.findById(id_place)
      .orElseThrow(() -> new PlaceNotFoundException(id_place));
    
    return new Resource<>(Place,
    	    linkTo(methodOn(PlaceController.class).one(id_place)).withSelfRel(),
    	    linkTo(methodOn(PlaceController.class).all()).withRel("Place"));
   }

//Pour modifier une place si besoin 

@PutMapping("/place/{id_place}")
  Place replacePlace(@RequestBody Place newPlace, @PathVariable Long id_place) {

    return repository.findById(id_place)
      .map(Place -> {
    	  Place.setNom_place(newPlace.getNom_place());
    	  Place.setPrix_final(newPlace.getPrix_final());
        return repository.save(Place);
      })
      .orElseGet(() -> {
        newPlace.setId_place(id_place);
        return repository.save(newPlace);
      });
  }

// Pour Supprimer une place

  @DeleteMapping("/place/{id_place}")
  void deletePlace(@PathVariable Long id_place) {
    repository.deleteById(id_place);
  }
}