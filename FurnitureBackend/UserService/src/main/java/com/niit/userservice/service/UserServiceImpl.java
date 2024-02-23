package com.niit.userservice.service;

import com.niit.userservice.MailAPI.EmailService;
import com.niit.userservice.exception.UserAlreadyExistException;
import com.niit.userservice.repository.UserRepository;
import com.niit.userservice.exception.UserNotFoundException;
import com.niit.userservice.model.User;
import com.niit.userservice.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;
    private UserProxy userProxy;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy){
        this.userRepository=userRepository;
        this.userProxy=userProxy;
    }

    EmailService emailService=new EmailService();

    @Override
    public User getUser(String userEmail) throws UserNotFoundException {
        if (userRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(userEmail).get();
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        System.out.println("Inside service under addUser method");
        if (userRepository.findById(user.getUserEmail()).isPresent()){
            System.out.println("Inside if under add user");
            throw new UserAlreadyExistException();
        }
        ResponseEntity r = userProxy.registerUser(user);
        System.out.println("Before email method calling inside add user");
        emailService.sendEmail(user);
        System.out.println("After email service in add user");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, String userEmail) throws UserNotFoundException {
        User getUser = userRepository.findById(userEmail).get();
        if(Optional.ofNullable(getUser).isEmpty()){
            throw new UserNotFoundException();
        }
        getUser.setUserName(user.getUserName());
        getUser.setUserPassword(user.getUserPassword());
        ResponseEntity r = userProxy.updateUser(getUser, userEmail);
        User  updateUser = userRepository.save(getUser);
        return updateUser;
    }

    @Override
    public boolean deleteUser(String userEmail) throws UserNotFoundException {
        boolean flag=false;
        if (userRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        else {
            userRepository.deleteById(userEmail);
            flag=true;
        }
        return flag;
    }

}



























