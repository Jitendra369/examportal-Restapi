package com.exam.models.exam;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	
	private String description;
	
//	Eager -> when we fetch cate then, all the quize can be excessable , cascade same as fetch
//	one catg. can have many quize, mapped with Quize table categ. col
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quize> quizes = new LinkedHashSet<>();
	

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	

	public Set<Quize> getQuizes() {
		return quizes;
	}

	public void setQuizes(Set<Quize> quizes) {
		this.quizes = quizes;
	}

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

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
	
	
	
}
