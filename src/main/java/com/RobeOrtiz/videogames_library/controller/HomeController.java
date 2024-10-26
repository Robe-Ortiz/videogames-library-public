package com.RobeOrtiz.videogames_library.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RobeOrtiz.videogames_library.entity.Videogame;
import com.RobeOrtiz.videogames_library.service.StatisticsService;
import com.RobeOrtiz.videogames_library.service.VideogameService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private VideogameService videogameService;
	@Autowired
	private StatisticsService statisticsService;


	
	@RequestMapping("/")
	public String home(HttpSession session, Model model) {
		List<Videogame> featured = videogameService.searchForFeatured();
		model.addAttribute("videogames",featured);
		
		long totalVisits;
		if(session.getAttribute("hasVisitedVideogameLibrary")== null) {
			totalVisits = statisticsService.incrementAndGetTotalVisits(); 
			session.setAttribute("hasVisitedVideogameLibrary", true);		
		}else {
			totalVisits = statisticsService.getTotalVisits(); 
		}		
		
		if(totalVisits >= 0) 
			model.addAttribute("showVisitsCounter", true);
			
		model.addAttribute("visits", totalVisits);					
		return "home";
	}
	
	@RequestMapping("/videogameForDevelopmentTeam")
	public String videogameForDevelopmentTeam(int developmentTeamId, Model model) {
		List<Videogame> videogames = videogameService.searchForDevelopmentTeam(developmentTeamId);
		model.addAttribute("videogames", videogames);
		return "home";
	}
	
	@RequestMapping("/videogameInfo")
	public String videogameInfo(int videogameId, Model model) {
		Videogame videogame = videogameService.searchForID(videogameId);
		model.addAttribute("videogame", videogame);
		return "videogame-info";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("q") String query, Model model) {
		Set<Videogame> videogames = videogameService.search(query);
		model.addAttribute("videogames",videogames);
		return "home";
	}
	
	@RequestMapping("/randomVideogame")
	public String randomVideogame(Model model) {
		List<Videogame> videogames = videogameService.searchForFeatured();
		Collections.shuffle(videogames);
		model.addAttribute("videogame", videogames.get(0));
		return "videogame-info";
	}
}
