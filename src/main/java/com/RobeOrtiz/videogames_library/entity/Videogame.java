package com.RobeOrtiz.videogames_library.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Videogame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private String descriptionLarge;
	private String imageUrl;
	private String videoUrl;
	
	@ManyToOne
	private DevelopmentTeam developmentTeam;
		
	public Videogame() {}

	public Videogame(Integer id, String name, String description, String descriptionLarge, String imageUrl,
			String videoUrl, DevelopmentTeam developmentTeam) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.descriptionLarge = descriptionLarge;
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
		this.developmentTeam = developmentTeam;
	}


	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	
	public DevelopmentTeam getDevelopmentTeam() {
		return developmentTeam;
	}
		
	public String getDescriptionLarge() {
		return descriptionLarge;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public void setDescriptionLarge(String descriptionLarge) {
		this.descriptionLarge = descriptionLarge;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDevelopmentTeam(DevelopmentTeam developmentTeam) {
		this.developmentTeam = developmentTeam;
	}

	@Override
	public String toString() {
		return "Videogame [id=" + id + ", name=" + name + ", description=" + description + ", descriptionLarge="
				+ descriptionLarge + ", imageUrl=" + imageUrl + ", developmentTeam=" + developmentTeam + "]";
	}


	
	
	
}
