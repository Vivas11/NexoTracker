package com.vivascruz.nexotracker.riot;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;

@Service
public class RiotApiClient {

	private final String token = "RGAPI-9ac062b4-8f03-4e91-a2f0-4a83472c7031";

	private final Map<String, RiotRegion> regions = new HashMap<>();

	private final Map<String, String> regionalRoutes = new HashMap<>();

	private final Map<String, String> platformRoutes = new HashMap<>();

	private final HttpClient client = HttpClient.newHttpClient();

	public RiotApiClient() {

		// Routing regional
		regionalRoutes.put("AMERICAS", "https://americas.api.riotgames.com");

		regionalRoutes.put("EUROPE", "https://europe.api.riotgames.com");

		regionalRoutes.put("ASIA", "https://asia.api.riotgames.com");

		// Routing plataforma
		platformRoutes.put("LA1", "https://la1.api.riotgames.com");

		platformRoutes.put("LA2", "https://la2.api.riotgames.com");

		platformRoutes.put("NA1", "https://na1.api.riotgames.com");

		platformRoutes.put("BR1", "https://br1.api.riotgames.com");

		platformRoutes.put("EUW1", "https://euw1.api.riotgames.com");

		platformRoutes.put("EUN1", "https://eun1.api.riotgames.com");

		platformRoutes.put("KR", "https://kr.api.riotgames.com");

		platformRoutes.put("JP1", "https://jp1.api.riotgames.com");

		// Relación servidor -> región + plataforma
		regions.put("LA1", new RiotRegion("AMERICAS", "LA1"));

		regions.put("LA2", new RiotRegion("AMERICAS", "LA2"));

		regions.put("NA1", new RiotRegion("AMERICAS", "NA1"));

		regions.put("BR1", new RiotRegion("AMERICAS", "BR1"));

		regions.put("EUW1", new RiotRegion("EUROPE", "EUW1"));

		regions.put("EUN1", new RiotRegion("EUROPE", "EUN1"));

		regions.put("KR", new RiotRegion("ASIA", "KR"));

		regions.put("JP1", new RiotRegion("ASIA", "JP1"));
	}

	/*
	 * Busca la cuenta por Riot ID Usa routing regional
	 */
	public RiotAccountDTO summonerByTag(String name, String tag, String server) {

		RiotRegion region = getRegion(server);

		String url = regionalRoutes.get(region.getRegion()) + "/riot/account/v1/accounts/by-riot-id/" + name + "/"
				+ tag;

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("X-Riot-Token", token).GET().build();

		try {

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(response.body(), RiotAccountDTO.class);

		} catch (Exception e) {

			throw new RuntimeException("Error obteniendo Riot Account", e);
		}
	}

	/*
	 * Busca información del invocador Usa servidor de plataforma
	 */
	public HttpResponse<String> summonerByPuuid(String puuid, String server) {

		RiotRegion region = getRegion(server);

		String url = platformRoutes.get(region.getPlatform()) + "/lol/summoner/v4/summoners/by-puuid/" + puuid;

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("X-Riot-Token", token).GET().build();

		try {

			return client.send(request, HttpResponse.BodyHandlers.ofString());

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