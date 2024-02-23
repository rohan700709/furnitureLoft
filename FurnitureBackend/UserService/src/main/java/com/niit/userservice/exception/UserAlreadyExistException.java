package com.niit.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User Details is already Exist!!")
public class UserAlreadyExistException extends Exception{
}
