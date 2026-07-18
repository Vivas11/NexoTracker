package com.vivascruz.nexotracker.summoner.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivascruz.nexotracker.riot.RiotApiClient;
import com.vivascruz.nexotracker.summoner.service.SummonerService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/summoner")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081" })
@Transactional
@Tag(name = "Summoners Management", description = "Endpoints for managing summoners")
public class SummonerController {
	@Autowired
	private SummonerService summonerService;

	@Autowired
	private RiotApiClient riotApi;

	public SummonerController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/getSummoner")
	public ResponseEntity<String> getSummoner(@RequestParam String name, @RequestParam String tag,
			@RequestParam String server) {

		String puuid = riotApi.summonerByTag(name, tag, server).getPuuid();
		System.out.println(puuid);
		HttpResponse<String> response = riotApi.summonerByPuuid(puuid, server);

		return ResponseEntity.ok(response.body());
	}
}
