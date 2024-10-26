package com.RobeOrtiz.videogames_library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Statistics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long totalVisits;

	public Statistics() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(long totalVisits) {
		this.totalVisits = totalVisits;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
