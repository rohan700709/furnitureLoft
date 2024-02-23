package com.niit.userAuthenticationService.service;


import com.niit.userAuthenticationService.exception.UserAlreadyExistsException;
import com.niit.userAuthenticationService.exception.UserNotFoundException;
import com.niit.userAuthenticationService.model.User;

public interface UserAuthService {

//    Modifier 'public' is redundant for interface members

     User loginUser(String userEmail, String userPassword) throws UserNotFoundException;
     User addNewUser(User user) throws UserAlreadyExistsException;
     User updateUserDetails(User user, String userEmail) throws UserNotFoundException;
     void initRolesAndUser();
}
