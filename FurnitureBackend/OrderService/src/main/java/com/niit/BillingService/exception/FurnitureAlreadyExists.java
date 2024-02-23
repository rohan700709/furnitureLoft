package com.niit.BillingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CREATED, reason = "Furniture already exists!!")
public class FurnitureAlreadyExists extends Exception{
}
