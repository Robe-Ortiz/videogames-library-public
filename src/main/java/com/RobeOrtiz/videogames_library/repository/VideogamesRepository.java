package com.RobeOrtiz.videogames_library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RobeOrtiz.videogames_library.entity.DevelopmentTeam;
import com.RobeOrtiz.videogames_library.entity.Videogame;

public interface VideogamesRepository extends JpaRepository<Videogame, Integer> {

	//search for all videogames in alphabetical order by name
	@Query("from Videogame video order by video.name")
	List<Videogame> searchAll();
	
	//search videogames filtered by developer
	@Query("from Videogame v where v.developmentTeam.id = ?1 order by v.name")
	List<Videogame> searchForDevelopmentTeam(int developmentTeamId);
	 
	//search a videogames filtered by name
	List<Videogame> findByNameContaining(String name);
	
	//searh videogames filtered by DevelopmentTeam name
	@Query("from Videogame v where v.developmentTeam.name LIKE %?1%")
	List<Videogame> findByDevelopmentTeamName(String developmentTeamName);
}
