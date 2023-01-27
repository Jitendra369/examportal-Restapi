package com.exam.repo;

import com.exam.models.User;
import org.hibernate.id.factory.IdentifierGeneratorFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {

//find user having perticular username
    User findByUsername(String username);
    void deleteUserByUsername(String username);
}
