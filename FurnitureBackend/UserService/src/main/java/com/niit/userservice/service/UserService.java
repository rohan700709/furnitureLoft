package com.niit.userservice.service;

import com.niit.userservice.exception.UserAlreadyExistException;
import com.niit.userservice.exception.UserNotFoundException;
import com.niit.userservice.model.User;

public interface UserService {

    User getUser(String userEmail) throws UserNotFoundException;
    User addUser(User user) throws UserAlreadyExistException;
    User updateUser(User user, String userEmail) throws UserNotFoundException;
    boolean deleteUser(String userEmail) throws UserNotFoundException;
}
