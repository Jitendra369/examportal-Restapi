package com.exam.service;

import java.util.Set;

import com.exam.models.exam.Questions;
import com.exam.models.exam.Quize;

public interface QuestionService {
	
	public Questions saveQuestions(Questions question);
	
	public Questions updateQuestion(Questions question);
	
	public Questions getQuestion(Long questionId);
	
	public Set<Questions> getAllQuestions();
	
	public void deleteQuestion(Long questionId);
	
//	get All the question from this Quize object
	public Set<Questions> getQuestionFromQuize(Quize quize);

}
