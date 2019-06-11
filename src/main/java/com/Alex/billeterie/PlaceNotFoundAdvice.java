package com.Alex.billeterie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class PlaceNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(PlaceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String placeNotFoundHandler(PlaceNotFoundException ex) {
    return ex.getMessage();
  }
}
