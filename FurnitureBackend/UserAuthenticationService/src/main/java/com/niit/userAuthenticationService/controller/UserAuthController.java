package com.niit.userAuthenticationService.controller;

import com.niit.userAuthenticationService.exception.UserAlreadyExistsException;
import com.niit.userAuthenticationService.model.User;
import com.niit.userAuthenticationService.security.SecurityTokenGenerator;
import com.niit.userAuthenticationService.service.UserAuthService;
import com.niit.userAuthenticationService.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/v1/")
@CrossOrigin(origins="*")
@RestController
public class UserAuthController {

    private UserAuthService userAuthService;
    private SecurityTokenGenerator serviceTokenGenerator;

    @Autowired
    public UserAuthController(UserAuthService userAuthService, SecurityTokenGenerator serviceTokenGenerator) {
        this.userAuthService = userAuthService;
        this.serviceTokenGenerator = serviceTokenGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            System.out.println("Inside of proxy established registerUser method");
            userAuthService.addNewUser(user);
            return new ResponseEntity(userAuthService.addNewUser(user), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        System.out.println(user);
        Map<String, String> map = new HashMap<>();
        try {
            User userObj = userAuthService.loginUser(user.getUserEmail(), user.getUserPassword());
            if (userObj.getUserEmail().equals(user.getUserEmail())) {
                System.out.println("Inside If:"+userObj.getUserEmail());
                map = serviceTokenGenerator.generateToken(userObj);
            }
            return new ResponseEntity(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity("Internal Server Error!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{userEmail}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("userEmail") String userEmail) throws UserNotFoundException {
        return new ResponseEntity<>(userAuthService.updateUserDetails(user,userEmail),HttpStatus.OK);
    }

    @PostConstruct
    public void initRolesAndUsers(){
        userAuthService.initRolesAndUser();
    }
}
