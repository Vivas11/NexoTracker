package com.vivascruz.nexotracker.riot;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivascruz.nexotracker.summoner.dto.SummonerDto;

import tools.jackson.databind.ObjectMapper;

@Service
public class RiotApiClient {

	@Autowired
	private ObjectMapper objectMapper;
	
	private final String token = "RGAPI-abb80456-eaf5-426a-aea2-87795e278d7e";

	private final Map<String, RiotRegion> regions = new HashMap<>();

	private final Map<String, String> regionalRoutes = new HashMap<>();

	private final Map<String, String> platformRoutes = new HashMap<>();

	private final HttpClient client = HttpClient.newHttpClient();

	public RiotApiClient() {

		// Routing regional
		regionalRoutes.put("AMERICAS", "https://americas.api.riotgames.com");

		regionalRoutes.put("EUROPE", "https://europe.api.riotgames.com");

		regionalRoutes.put("ASIA", "https://asia.api.riotgames.com");

		// Plataforma -> host de la API (la URL mantiene el número, la clave no)
		platformRoutes.put("BR", "https://br1.api.riotgames.com");
		platformRoutes.put("EUN", "https://eun1.api.riotgames.com");
		platformRoutes.put("EUW", "https://euw1.api.riotgames.com");
		platformRoutes.put("JP", "https://jp1.api.riotgames.com");
		platformRoutes.put("KR", "https://kr.api.riotgames.com");
		platformRoutes.put("LAN", "https://la1.api.riotgames.com");
		platformRoutes.put("LAS", "https://la2.api.riotgames.com");
		platformRoutes.put("ME", "https://me1.api.riotgames.com");
		platformRoutes.put("NA", "https://na1.api.riotgames.com");
		platformRoutes.put("OC", "https://oc1.api.riotgames.com");
		platformRoutes.put("RU", "https://ru.api.riotgames.com");
		platformRoutes.put("SG", "https://sg2.api.riotgames.com");
		platformRoutes.put("TR", "https://tr1.api.riotgames.com");
		platformRoutes.put("TW", "https://tw2.api.riotgames.com");
		platformRoutes.put("VN", "https://vn2.api.riotgames.com");

		// Relación servidor -> región + plataforma
		regions.put("BR", new RiotRegion("AMERICAS", "BR"));
		regions.put("EUN", new RiotRegion("EUROPE", "EUN"));
		regions.put("EUW", new RiotRegion("EUROPE", "EUW"));
		regions.put("JP", new RiotRegion("ASIA", "JP"));
		regions.put("KR", new RiotRegion("ASIA", "KR"));
		regions.put("LAN", new RiotRegion("AMERICAS", "LAN"));
		regions.put("LAS", new RiotRegion("AMERICAS", "LAS"));
		regions.put("ME", new RiotRegion("EUROPE", "ME"));
		regions.put("NA", new RiotRegion("AMERICAS", "NA"));
		regions.put("OC", new RiotRegion("SEA", "OC"));
		regions.put("RU", new RiotRegion("EUROPE", "RU"));
		regions.put("SG", new RiotRegion("SEA", "SG"));
		regions.put("TR", new RiotRegion("EUROPE", "TR"));
		regions.put("TW", new RiotRegion("SEA", "TW"));
		regions.put("VN", new RiotRegion("SEA", "VN"));
	}

	/*
	 * Busca la cuenta por Riot ID Usa routing regional
	 */
	public RiotAccountDto summonerByTag(String name, String tag, String server) {
		
		RiotRegion region = getRegion(server);

		String url = regionalRoutes.get(region.getRegion()) + "/riot/account/v1/accounts/by-riot-id/" + name.replace(" ", "%20") + "/"
				+ tag;

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("X-Riot-Token", token).GET().build();

		try {

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(response.body(), RiotAccountDto.class);

		} catch (Exception e) {

			throw new RuntimeException("Error obteniendo Riot Account", e);
		}
	}

	/*
	 * Busca información del invocador Usa servidor de plataforma
	 */
	public RiotSummonerDto summonerByPuuid(String puuid, String server) {

		RiotRegion region = getRegion(server);

		String url = platformRoutes.get(region.getPlatform()) + "/lol/summoner/v4/summoners/by-puuid/" + puuid;

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("X-Riot-Token", token).GET().build();

		try {
			HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
			
			RiotSummonerDto dto = objectMapper.readValue(response.body(), RiotSummonerDto.class);
			
			return dto;
			
			

		} catch (Exception e) {

			throw new RuntimeException("Error obteniendo Summoner", e);
		}

	}

	public RiotRegion getRegion(String server) {

		RiotRegion region = regions.get(server);

		if (region == null) {
			throw new RuntimeException("Servidor Riot no soportado: " + server);
		}

		return region;
	}

}