package com.RobeOrtiz.videogames_library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RobeOrtiz.videogames_library.entity.Videogame;
import com.RobeOrtiz.videogames_library.service.DevelopmentTeamService;
import com.RobeOrtiz.videogames_library.service.VideogameService;


@Controller
public class VideogameCrudController {

	private final DevelopmentTeamService developmentTeamService;
	private final VideogameService videogameService;

	public VideogameCrudController(DevelopmentTeamService developmentTeamService, VideogameService videogameService) {
		this.developmentTeamService = developmentTeamService;
		this.videogameService = videogameService;
	}

	@RequestMapping("/videogame/add")
	public String showRegistrationFormForAVideogame(Model model) {
		model.addAttribute("title", "Nuevo Videojuego");
		model.addAttribute("developmentTeams", developmentTeamService.searchAll());
		model.addAttribute("videogame",new Videogame());
		return "videogame-form";
	}
	
	@PostMapping("/videogame/save")
	public String save(@ModelAttribute("videogame") Videogame videogame) {
		videogameService.save(videogame);
		return "redirect:/";
	}
	
	
}
