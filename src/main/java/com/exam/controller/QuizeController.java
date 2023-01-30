package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.sql.Update;
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

import com.exam.impl.QuizeServiceImpl;
import com.exam.models.exam.Quize;

import net.bytebuddy.asm.Advice.Return;

@RestController
@CrossOrigin("*")
@RequestMapping("/quize")
public class QuizeController {
	
	@Autowired
	private QuizeServiceImpl quizeServiceImpl;

//	create quize handler
	@PostMapping("/")
	public ResponseEntity<Quize> createQuize(@RequestBody Quize quize) {
		Quize quize1 = this.quizeServiceImpl.addQuize(quize);
		return ResponseEntity.ok(quize1);
	}
	
//	get quize handler
	@GetMapping("/{quizeId}")
	public ResponseEntity<Quize> getQuize(@PathVariable("quizeId") int quizeId ){
		Quize quize = this.quizeServiceImpl.getQuize(quizeId);
		return ResponseEntity.ok(quize);
	}
	
//	update the quize
	@PutMapping("/")
	public ResponseEntity<?> updateQuize(@RequestBody Quize quize){
		Quize updateQuize = this.quizeServiceImpl.updateQuize(quize);
		return ResponseEntity.ok(updateQuize);
	}
	
//	get all quize handler 
	@GetMapping("/")
	public ResponseEntity<Set<Quize>> getAllQuize(){
		return ResponseEntity.ok(this.quizeServiceImpl.getAllQuize());
	}
	
//	delete the quize
	@DeleteMapping("/{quizeId}")
	public ResponseEntity<?> deleteQuize(@PathVariable("quizeId") int quizeId) {
		System.out.println("deleting id " + quizeId);
		this.quizeServiceImpl.deleteQuize(quizeId);
		return ResponseEntity.ok("quize deleted");
	}
	
	
}
