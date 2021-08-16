package com.example.demo.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.CovidDataServices;

import models.Stats;

@Controller
public class HomeController {

	@Autowired
	CovidDataServices covidDataServices;
	@GetMapping("/")
	public String home(Model model) {
		List<Stats> allStats=covidDataServices.getAllrecord();
		
		int totalCases=allStats.stream().mapToInt(stat -> stat.getLatestCases()).sum();
		
		model.addAttribute("Stats",covidDataServices.getAllrecord());
		model.addAttribute("totalCases", totalCases);
		
		return "home";
	}
}
