package com.RobeOrtiz.videogames_library.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.RobeOrtiz.videogames_library.entity.Videogame;
import com.RobeOrtiz.videogames_library.repository.VideogamesRepository;

@Service
public class VideogameService {
	
	private final VideogamesRepository videogameRepository;
	
	

	public VideogameService(VideogamesRepository videogameRepository) {
		this.videogameRepository = videogameRepository;
	}


	//search for all videogames in alphabetical order by name
	public List<Videogame> searchForFeatured(){		
		return videogameRepository.searchAll();
	}
	
	//search videogames filtered by developer
	public List<Videogame> searchForDevelopmentTeam(int developmentTeamId){
		return videogameRepository.searchForDevelopmentTeam(developmentTeamId);
	}
	
	public Videogame searchForID(int videogameId) {
		return videogameRepository.findById(videogameId).get();
	}
	
	//search a videogame filtered by name
	public Set<Videogame> search(String query){
		Set<Videogame> videogameSet = new HashSet<>();
		videogameSet.addAll(videogameRepository.findByNameContaining(query));
		videogameSet.addAll(videogameRepository.findByDevelopmentTeamName(query));
		return videogameSet;
	}
	
	// save a videogame in the database
	public Videogame save(Videogame videogame) {
		return videogameRepository.save(videogame);
	}
}
