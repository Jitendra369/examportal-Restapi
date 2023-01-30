package com.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.impl.QuestionServiceImpl;
import com.exam.impl.QuizeServiceImpl;
import com.exam.models.exam.Questions;
import com.exam.models.exam.Quize;

import io.jsonwebtoken.lang.Collections;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	
	@Autowired
	private QuizeServiceImpl quizeServiceImpl;
	
//	save question handler
	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Questions questions) {
		Questions saveQuestions = this.questionServiceImpl.saveQuestions(questions);
		return ResponseEntity.ok(saveQuestions);
	}
	
//	get all the question for perticular quize
//	quize may have any number of question , quize has limit on number of question 
//	while, we can create any number of question, so we have to display only the question present in max number of question which quize have 
	@GetMapping("/quize/{quizeId}")
	public ResponseEntity<?> getAllQuestionformQuize(@PathVariable("quizeId") int quizeId){
		
		Quize quize = this.quizeServiceImpl.getQuize(quizeId);
		Set<Questions> questions = quize.getQuestions();
		List<Questions> queList  = new ArrayList<>(questions);
		if (queList.size() > Integer.parseInt(quize.getNumberOfQuestions())) {
			queList = queList.subList(0, Integer.parseInt(quize.getNumberOfQuestions()+1));
		}
//		re-order the quize
		java.util.Collections.shuffle(queList);
		return ResponseEntity.ok(questions);
	}
	
//	get single-question handler
	@GetMapping("/{questionId}")
	public ResponseEntity<?> getQuestion(@PathVariable("questionId") Long questionId ){
		Questions question = this.questionServiceImpl.getQuestion(questionId);
		return ResponseEntity.ok(question);
	}
	
//	get All question handler
	@GetMapping("/")
	public ResponseEntity<Set<Questions>> getAllQuestions(){
		Set<Questions> allQuestions = this.questionServiceImpl.getAllQuestions();
		return ResponseEntity.ok(allQuestions);
	}
	
//	update the question
	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@RequestBody Questions questions){
		Questions updateQuestion = this.questionServiceImpl.updateQuestion(questions);
		return ResponseEntity.ok(updateQuestion);
	}
	
//	delete question handler
	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestions(@PathVariable("questionId") Long questionId){
		this.questionServiceImpl.deleteQuestion(questionId);
		return ResponseEntity.ok("question has been deleted");
	}
}
