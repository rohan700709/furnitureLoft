package com.niit.userservice.proxy;

import com.niit.userservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-authentication-service", url="localhost:8082/api/v1/")
public interface UserProxy {

    @PostMapping("register")
    ResponseEntity<?> registerUser(@RequestBody User user);

    @PutMapping("update/{userEmail}")
    ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("userEmail") String userEmail);
}
