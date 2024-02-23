package com.niit.userAuthenticationService.service;

import com.niit.userAuthenticationService.model.User;
import com.niit.userAuthenticationService.repository.UserAuthRepository;
import com.niit.userAuthenticationService.exception.UserAlreadyExistsException;
import com.niit.userAuthenticationService.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private UserAuthRepository userAuthRepository;
    @Autowired
    public UserAuthServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public User loginUser(String userEmail, String userPassword) throws UserNotFoundException {
        User user = userAuthRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User addNewUser(User user) throws UserAlreadyExistsException {
        System.out.println("Inside add new user in auth microservice"+user);
//        if (userAuthRepository.findById(user.getUserEmail()).isPresent()) {
//            throw new UserAlreadyExistsException();
//        }
        user.setUserRole("User");
        System.out.println("After setting role"+user);
        userAuthRepository.save(user);
        return userAuthRepository.findById(user.getUserEmail()).get();
    }


    @Override
    public User updateUserDetails(User user, String userEmail) throws UserNotFoundException {
        User getUser = userAuthRepository.findById(userEmail).get();
        if(Optional.ofNullable(getUser).isEmpty()){
            throw new UserNotFoundException();
        }
        getUser.setUserPassword(user.getUserPassword());
        User  updateUser = userAuthRepository.save(getUser);
        return updateUser;
    }

    @Override
    public void initRolesAndUser(){
        User adminUser = new User();
        adminUser.setUserEmail("root@gmail.com");
        adminUser.setUserPassword("root");
        adminUser.setUserRole("Admin");
        userAuthRepository.save(adminUser);
    }

}
