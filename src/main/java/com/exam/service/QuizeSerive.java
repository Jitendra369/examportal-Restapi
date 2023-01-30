package com.exam.service;

import java.util.Set;

import com.exam.models.exam.Quize;

public interface QuizeSerive {
	
	public Quize addQuize(Quize quize);
	
	public Quize updateQuize(Quize quize);
	
	public Set<Quize> getAllQuize();
	
	public Quize getQuize(int quizeId);
	
	public void deleteQuize(int quizeId);

}
