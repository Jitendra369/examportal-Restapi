package com.exam.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.models.exam.Questions;
import com.exam.models.exam.Quize;
import com.exam.repo.QuestionRepo;
import com.exam.repo.QuizeRepo;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private QuizeRepo quizeRepo;

	@Override
	public Questions saveQuestions(Questions question) {
		return this.questionRepo.save(question);
	}

	@Override
	public Questions updateQuestion(Questions question) {
		return this.questionRepo.save(question);
	}

	@Override
	public Questions getQuestion(Long questionId) {
		return this.questionRepo.findById(questionId).get();
	}

	@Override
	public Set<Questions> getAllQuestions() {
		return new HashSet<>(this.questionRepo.findAll());
	}

	@Override
	public void deleteQuestion(Long questionId) {
		this.questionRepo.deleteById(questionId);
	}

	@Override
	public Set<Questions> getQuestionFromQuize(Quize quize) {
		return this.questionRepo.findByQuize(quize);
	}

}
