package com.niit.userservice.controller;


import com.niit.userservice.exception.UserAlreadyExistException;
import com.niit.userservice.exception.UserNotFoundException;
import com.niit.userservice.model.User;
import com.niit.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController

@RequestMapping("api/v2/")
public class UserController {

    private UserService userService;
    private ResponseEntity responseEntity;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("user/{userEmail}")
    public ResponseEntity<?> getUser(@PathVariable("userEmail") String userEmail) throws UserNotFoundException {
        try{
            userService.getUser(userEmail);
            responseEntity=new ResponseEntity<>("Success!!",HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();}
        catch (Exception e){
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("user")
    public ResponseEntity<?> addUser(@RequestBody User user)throws UserAlreadyExistException {
        System.out.println("Outside of Try "+user);
        try {
            System.out.println("Just Inside of Try "+user);
            userService.addUser(user);
            System.out.println("Inside of Try 2 "+user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            throw new UserAlreadyExistException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
        }

    @PutMapping("user/{userEmail}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("userEmail") String userEmail) throws UserNotFoundException {
        try{

            responseEntity=new ResponseEntity<>(userService.updateUser(user,userEmail),HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();}
        catch (Exception e){
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("user/{userEmail}")
    public ResponseEntity<?> deleteUser(@PathVariable String userEmail)throws UserNotFoundException{
        try{
            userService.deleteUser(userEmail);
            responseEntity=new ResponseEntity<>("Successfully Deleted!!",HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();}
        catch (Exception e){
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}































