package com.vivascruz.nexotracker.summoner.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivascruz.nexotracker.riot.RiotAccountDto;
import com.vivascruz.nexotracker.riot.RiotApiClient;
import com.vivascruz.nexotracker.riot.RiotSummonerDto;
import com.vivascruz.nexotracker.summoner.dto.SummonerDto;
import com.vivascruz.nexotracker.summoner.entity.Summoner;
import com.vivascruz.nexotracker.summoner.repository.SummonerRepository;

@Service
public class SummonerService {
	@Autowired
	private SummonerRepository summoRepo;

	@Autowired
	private RiotApiClient riotApi;
	
	@Autowired
	private ModelMapper modelMapper;

	
	public RiotSummonerDto getByPuuid(String puuid, String server) {
		

		try {
			RiotSummonerDto response = riotApi.summonerByPuuid(puuid, server);
		
			return response;
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public SummonerDto getByTag(String name, String tag, String server) {

		RiotAccountDto account = riotApi.summonerByTag(name, tag, server);
		
		String puuid = account.getPuuid();

		if(exists(puuid)) {
			return modelMapper.map(summoRepo.findByPuuid(puuid), SummonerDto.class);
		}
		
		
		RiotSummonerDto temp = getByPuuid(puuid, server);
		SummonerDto dto = new SummonerDto(account.getPuuid(), account.getGameName(), account.getTagLine(), server, temp.getProfileIconId(),  temp.getRevisionDate(), temp.getSummonerLevel());
		create(dto);
		return dto;
	}

	
	public long count() {
		return summoRepo.count();
	}

	public boolean exists(String puuid) {
		return summoRepo.existsByPuuid(puuid) ? true : false;
	}

	public int create(SummonerDto data) {
		if (summoRepo.existsByPuuid(data.getPuuid())) {
			return 409;
		}
		try {
			Summoner entity = modelMapper.map(data, Summoner.class);
			summoRepo.save(entity);
			return 200;
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} 
	}

	public List<SummonerDto> getAll() {
		List<Summoner> entityList = summoRepo.findAll();
		List<SummonerDto> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {

			SummonerDto dto = modelMapper.map(entity, SummonerDto.class);
			dtoList.add(dto);
		});

		return dtoList;
	}

	public int deleteByPuuid(String id) {
		Optional<Summoner> found = summoRepo.findByPuuid(id);
		if (found.isPresent()) {
			summoRepo.delete(found.get());
			return 200;
		} else {
			return 404;
		}
	}

	public int updateById(String id, SummonerDto newData) {
		Optional<Summoner> found = summoRepo.findByPuuid(id);

		if (found.isPresent()) {
			Summoner temp = found.get();
			temp.setProfileIconId(newData.getProfileIconId());
			temp.setRevisionDate(newData.getRevisionDate());
			temp.setSummonerLevel(newData.getSummonerLevel());
			summoRepo.save(temp);
			return 200;
		}
		if (!found.isPresent()) {
			return 404;
		} else {
			return 500;
		}
	}
}
