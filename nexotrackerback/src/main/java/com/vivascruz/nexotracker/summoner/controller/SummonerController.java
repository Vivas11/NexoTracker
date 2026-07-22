package com.vivascruz.nexotracker.summoner.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivascruz.nexotracker.riot.RiotApiClient;
import com.vivascruz.nexotracker.summoner.dto.SummonerDto;
import com.vivascruz.nexotracker.summoner.service.SummonerService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/summoner")
@Transactional
@Tag(name = "Summoners Management", description = "Endpoints for managing summoners")
public class SummonerController {
	@Autowired
	private SummonerService summonerService;

	public SummonerController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/getSummonerByTag")
	public ResponseEntity<SummonerDto> getSummoner(@RequestParam String name, @RequestParam String tag,
			@RequestParam String server) {

        return ResponseEntity.status(200).body(summonerService.getByTag(name, tag, server));
	}
	
}
