package com.exam.models.exam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quize {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	
	private String description;
	
	private int maxMarks;
	
	private String numberOfQuestions;
	
	private boolean quizeStatus= false;
	
//	many-to-one relationship
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "quize", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Questions> questions = new HashSet<>();
	
	

	public void setId(int id) {
		this.id = id;
	}

	public Set<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Quize() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quize(String title, String description, int maxMarks, String numberOfQuestions, boolean quizeStatus) {
		super();
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberOfQuestions = numberOfQuestions;
		this.quizeStatus = quizeStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(String numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public boolean isQuizeStatus() {
		return quizeStatus;
	}

	public void setQuizeStatus(boolean quizeStatus) {
		this.quizeStatus = quizeStatus;
	}

	public int getId() {
		return id;
	}
	
	
	

}
