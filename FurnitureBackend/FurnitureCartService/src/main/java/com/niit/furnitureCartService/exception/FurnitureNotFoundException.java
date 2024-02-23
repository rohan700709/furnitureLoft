package com.niit.furnitureCartService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Furniture Not Found!!")
public class FurnitureNotFoundException extends Exception{
}
