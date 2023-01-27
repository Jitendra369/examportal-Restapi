package com.exam.impl;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepo;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepository roleRepository;

//    creating user
    @Override
    public User createUser(User user, Set<UserRole> userRole) throws Exception {
//        check if the user is present or not
        User localUser = this.userRepo.findByUsername(user.getUsername());
        if (localUser!=null){
            System.out.println("User is Al-Ready Present");
            throw new Exception("User Already Present");
        }else{
//            create user

//            save the role
//            Here, we only save userRole while saving the user, in casecade=ALL operation
            for(UserRole uRole : userRole){
                this.roleRepository.save(uRole.getRole());
            }

            user.getUserRoles().addAll(userRole);
//            save the user
            localUser = this.userRepo.save(user);
        }

        return localUser;
    }

    @Override
    public User getUser(String username) {
       return this.userRepo.findByUsername(username);
    }



//    @Override   //foreign key error
//    @Transactional
//    public void deleteUser(String username) {
//        this.userRepo.deleteUserByUsername(username);
//    }

//    update user

    // TODO: 1/22/2023 --> not tested, test before use this API
    public User updateUser(User user, Long userId) {
        return this.userRepo.findById(userId).map(
                        user1 -> {
                            user1.setFirstName(user.getFirstName());
                            user1.setLastName(user.getLastName());
                            user1.setProfile(user.getProfile());
                            user1.setPassword(user.getPassword());
                            user1.setPhoneNo(user.getPhoneNo());
                            user1.setEmail(user.getEmail());

                            return this.userRepo.save(user1);
                        })
                .orElseGet(() -> {
                    return this.userRepo.save(user);
                });
    }
//        return this.userRepo.save(user);

    @Override
    public void deleteUser(Long userId) {
        this.userRepo.deleteById(userId);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }
}
