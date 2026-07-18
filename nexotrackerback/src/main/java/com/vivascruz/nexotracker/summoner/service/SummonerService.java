package com.vivascruz.nexotracker.summoner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivascruz.nexotracker.summoner.dto.SummonerDTO;
import com.vivascruz.nexotracker.summoner.entity.Summoner;
import com.vivascruz.nexotracker.summoner.repository.SummonerRepository;

@Service
public class SummonerService {
	@Autowired
	private SummonerRepository summoRepo;

	@Autowired
	private ModelMapper modelMapper;

	public long count() {
		return summoRepo.count();
	}

	public boolean exists(String puuid) {
		return summoRepo.existsByPuuid(puuid) ? true : false;
	}

	public int create(SummonerDTO data) {
		try {
			Summoner entity = modelMapper.map(data, Summoner.class);
			summoRepo.save(entity);
			return 200;
		} catch (Exception e) {
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
			temp.setProfileIconID(newData.getProfileIconID());
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
