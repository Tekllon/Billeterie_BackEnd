package com.Alex.billeterie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TribuneNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(TribuneNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String tribuneNotFoundHandler(TribuneNotFoundException ex) {
    return ex.getMessage();
  }
}
