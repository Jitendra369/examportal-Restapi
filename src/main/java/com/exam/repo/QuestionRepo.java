package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.models.exam.Questions;

public interface QuestionRepo extends JpaRepository<Questions, Long> {

}
