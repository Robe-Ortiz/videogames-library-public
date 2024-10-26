package com.RobeOrtiz.videogames_library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.RobeOrtiz.videogames_library.entity.DevelopmentTeam;
import com.RobeOrtiz.videogames_library.entity.Videogame;
import com.RobeOrtiz.videogames_library.repository.DevelopmentTeamRepository;

@Service
public class DevelopmentTeamService {

	private final DevelopmentTeamRepository developmentTeamRepository;
	
	public DevelopmentTeamService(DevelopmentTeamRepository developmentTeamRepository) {
		this.developmentTeamRepository = developmentTeamRepository;
	}

	//search for all developmentTeam in alphabetical order by name
	public List<DevelopmentTeam> searchAll(){
		return developmentTeamRepository.searchAll();
	}
	
	// save a videogame in the database
	public DevelopmentTeam save(DevelopmentTeam developmentTeam) {
		return developmentTeamRepository.save(developmentTeam);
	}
}
