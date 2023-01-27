package com.exam.service;

import com.exam.models.User;
import com.exam.models.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRole) throws Exception;

    public User getUser(String username);



//    update user
    public User updateUser(User user, Long userId);

    public void deleteUser(Long userId);

    public List<User> getAllUser();

}
