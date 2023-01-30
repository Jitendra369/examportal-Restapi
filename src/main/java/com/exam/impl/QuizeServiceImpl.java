package com.exam.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exam.models.exam.Quize;
import com.exam.repo.QuizeRepo;
import com.exam.service.QuizeSerive;

@Service
public class QuizeServiceImpl implements QuizeSerive{
	
	@Autowired
	private QuizeRepo quizeRepo;

	@Override
	public Quize addQuize(Quize quize) {
		return this.quizeRepo.save(quize);
	}

//	set old id with new Details -- save operation 
	@Override
	public Quize updateQuize(Quize quize) {
		return this.quizeRepo.save(quize);
	}

	@Override
	public Set<Quize> getAllQuize() {
		return new HashSet<>(this.quizeRepo.findAll());
	}

	@Override
	public Quize getQuize(int quizeId) {
		return this.quizeRepo.findById(quizeId).get();
	}

	@Override
	public void deleteQuize(int quizeId) {
		this.quizeRepo.deleteById(quizeId);
//		Quize quize = getQuize(quizeId);  --not working
//		Quize quize = new Quize();
//		quize.setId(quizeId);
//		this.quizeRepo.delete(quize);
	}

}
