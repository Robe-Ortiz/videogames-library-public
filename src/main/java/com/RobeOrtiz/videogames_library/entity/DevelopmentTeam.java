package com.RobeOrtiz.videogames_library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DevelopmentTeam {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private String webSite;
	private String imageUrl;
	
	
	public DevelopmentTeam() {}



	public DevelopmentTeam(Integer id, String name, String description, String webSite, String imageUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.webSite = webSite;
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getWebSite() {
		return webSite;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	@Override
	public String toString() {
		return "DevelopmentTeam [id=" + id + ", name=" + name + ", webSite=" + webSite + "]";
	}
}
