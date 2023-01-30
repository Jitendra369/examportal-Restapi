package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.exam.models.exam.Quize;

public interface QuizeRepo extends JpaRepository<Quize, Integer>{

}
