package com.exam.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.models.exam.Questions;
import com.exam.models.exam.Quize;

public interface QuestionRepo extends JpaRepository<Questions, Long> {

	Set<Questions> findByQuize(Quize quize);

}
