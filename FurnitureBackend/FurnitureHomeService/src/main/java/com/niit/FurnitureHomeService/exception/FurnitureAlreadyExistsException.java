package com.niit.FurnitureHomeService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Furniture Already Exist!!")
public class FurnitureAlreadyExistsException extends Exception {
}
