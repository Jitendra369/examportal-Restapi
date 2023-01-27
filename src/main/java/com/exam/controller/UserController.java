package com.exam.controller;

import com.exam.exceptions.ExceptionResponse;
import com.exam.exceptions.UserIdNotFoundException;
import com.exam.exceptions.UserNameNotFoundException;
import com.exam.helper.UserPresentException;
import com.exam.impl.UserServiceImpl;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleName("NORMAL");


        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        Set<UserRole> userRoleList = new HashSet<>();
        userRoleList.add(userRole);
//        here we not save user, but getThe userRole and then add to user & save the user
//        only save the user
        return this.userServiceImpl.createUser(user, userRoleList);
    }

//    get user through username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        User user  = this.userServiceImpl.getUser(username);
        if(user==null){
            throw new UserNameNotFoundException();
        }
        return  user;
    }

//    delete user-by-username
    @DeleteMapping("/{userId}")
    public void deleteUser( @PathVariable("userId") Long username){
//        this.userServiceImpl.deleteUser(username);
        try {
            this.userServiceImpl.deleteUser(username);
        }catch (UserIdNotFoundException e){
            throw new UserIdNotFoundException();
        }
    }

//    update the user
    @PutMapping("/update/{userid}")
    public ResponseEntity<?>  updateUser(@RequestBody User user, @RequestParam("userid") Long userid){
        User users = this.userServiceImpl.updateUser(user, userid);
        return ResponseEntity.ok(users);
    }

// getAll users
    @GetMapping("/allusers")
    public List<User> getAllUser(){
        return this.userServiceImpl.getAllUser();
    }

//    exception handling
    @ExceptionHandler(UserPresentException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandling(){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("User name not found ");
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setLocalTime(LocalDateTime.now());
        return ResponseEntity.ok(exceptionResponse);
    }
}
