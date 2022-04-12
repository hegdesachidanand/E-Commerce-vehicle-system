package com.niit.authenticationservice.controller;

import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserNotFoundException;
import com.niit.authenticationservice.service.SecurityTokenGenerator;
import com.niit.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/userregister")
    public ResponseEntity<?> addingUserData(@RequestBody User user){
        System.out.println(user);
        return new ResponseEntity<>(userService.saveUserDetails(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> findByUserNameAndPassword(@RequestBody User user) throws UserNotFoundException {
        System.out.println(user);
        Map <String,String> map=null;
        try {
           User user1= userService.findByUserNameAndPassword(user.getUserName(),user.getPassword());
            System.out.println("user1"+user1);
            if(user1.getUserName().equals(user.getUserName())){
                map=securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
        catch (Exception ex){
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
