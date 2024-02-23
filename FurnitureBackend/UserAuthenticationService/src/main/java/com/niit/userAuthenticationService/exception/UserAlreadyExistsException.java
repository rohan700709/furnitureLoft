package com.niit.userAuthenticationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User Already Exist Exception!!")
public class UserAlreadyExistsException extends Exception {
}
