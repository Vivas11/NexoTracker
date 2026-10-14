package com.vivascruz.nexotracker.summoner.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vivascruz.nexotracker.summoner.entity.Summoner;

public interface SummonerRepository extends JpaRepository<Summoner, String> {
	public Optional<Summoner> findByPuuid(String puuid);
	public boolean existsByPuuid(String puuid);
}