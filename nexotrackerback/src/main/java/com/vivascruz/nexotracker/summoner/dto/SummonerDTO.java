package com.vivascruz.nexotracker.summoner.dto;

import java.util.Objects;

public class SummonerDto {

	private String puuid;
	
	private String name;

	private String tag;

	private String server;

	private Integer profileIconId;

	private Long revisionDate;

	private Long summonerLevel;

	public SummonerDto() {
	}

	
	public SummonerDto(String puuid, String name, String tag, String server, Integer profileIconId, Long revisionDate,
			Long summonerLevel) {
		super();
		this.puuid = puuid;
		this.name = name;
		this.tag = tag;
		this.server = server;
		this.profileIconId = profileIconId;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
	}

	

	public String getPuuid() {
		return puuid;
	}


	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getServer() {
		return server;
	}


	public void setServer(String server) {
		this.server = server;
	}


	public Integer getProfileIconId() {
		return profileIconId;
	}


	public void setProfileIconId(Integer profileIconId) {
		this.profileIconId = profileIconId;
	}


	public Long getRevisionDate() {
		return revisionDate;
	}


	public void setRevisionDate(Long revisionDate) {
		this.revisionDate = revisionDate;
	}


	public Long getSummonerLevel() {
		return summonerLevel;
	}


	public void setSummonerLevel(Long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(puuid);
	}

	@Override
	public boolean equals(Object obj) {

	    if (this == obj)
	        return true;

	    if (obj == null || getClass() != obj.getClass())
	        return false;

	    SummonerDto other = (SummonerDto) obj;

	    return Objects.equals(puuid, other.puuid);
	}
	
}
