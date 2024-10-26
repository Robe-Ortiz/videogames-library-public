package com.RobeOrtiz.videogames_library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RobeOrtiz.videogames_library.entity.DevelopmentTeam;
import com.RobeOrtiz.videogames_library.service.DevelopmentTeamService;

@Controller
public class DevelopmentTeamCrudController {
	private final DevelopmentTeamService developmentTeamService;

	public DevelopmentTeamCrudController(DevelopmentTeamService developmentTeamService) {
		this.developmentTeamService = developmentTeamService;
	}
	
	@RequestMapping("/developmentTeam/add")
	public String showRegistrationFormForADevelopmentTeam(Model model) {
		model.addAttribute("title", "Nuevo Desarrollador");
		model.addAttribute("developmentTeam", new DevelopmentTeam());
		model.addAttribute("developmentTeams", developmentTeamService.searchAll());
		return "developmentTeam-form";
	}
	
	@PostMapping("/developmentTeam/save")
	public String save(@ModelAttribute("developmentTeam") DevelopmentTeam developmentTeam) {
		developmentTeamService.save(developmentTeam);
		return "redirect:/";
	}
	
	
}