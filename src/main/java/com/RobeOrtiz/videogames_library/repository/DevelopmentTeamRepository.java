package com.RobeOrtiz.videogames_library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RobeOrtiz.videogames_library.entity.DevelopmentTeam;

public interface DevelopmentTeamRepository extends JpaRepository<DevelopmentTeam, Integer> {

	//search for all developmentTeam in alphabetical order by name
	@Query("from DevelopmentTeam d order by d.name")
	List<DevelopmentTeam> searchAll();
}
