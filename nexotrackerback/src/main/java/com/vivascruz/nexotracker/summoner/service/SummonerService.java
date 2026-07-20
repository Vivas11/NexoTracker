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
import com.vivascruz.nexotracker.riot.RiotApiClient;
import com.vivascruz.nexotracker.summoner.dto.SummonerDTO;
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

	@Autowired
	private ObjectMapper objectMapper;
	
	public SummonerDTO getByPuuid(String puuid, String server) {
		
		if(exists(puuid)) {
			return modelMapper.map(summoRepo.findByPuuid(puuid), SummonerDTO.class);
		}
		

		try {
		HttpResponse<String> response = riotApi.summonerByPuuid(puuid, server);
		
		SummonerDTO dto = objectMapper.readValue(response.body(), SummonerDTO.class);

        create(dto);

        return modelMapper.map(summoRepo.findByPuuid(puuid), SummonerDTO.class);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public SummonerDTO getByTag(String name, String tag, String server) {

		String puuid = riotApi.summonerByTag(name, tag, server).getPuuid();
		
		return getByPuuid(puuid, server);
		
	}

	public long count() {
		return summoRepo.count();
	}

	public boolean exists(String puuid) {
		return summoRepo.existsByPuuid(puuid) ? true : false;
	}

	public int create(SummonerDTO data) {
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

	public List<SummonerDTO> getAll() {
		List<Summoner> entityList = summoRepo.findAll();
		List<SummonerDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {

			SummonerDTO dto = modelMapper.map(entity, SummonerDTO.class);
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

	public int updateById(String id, SummonerDTO newData) {
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
