package com.vivascruz.nexotracker.riot;

import java.util.Objects;

public class RiotSummonerDto {

	private String puuid;

	private Integer profileIconId;

	private Long revisionDate;

	private Long summonerLevel;

	public RiotSummonerDto() {
	}
	public RiotSummonerDto(String puuid, Integer profileIconId, Long revisionDate, Long summonerLevel) {
		super();
		this.puuid = puuid;
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

	    RiotSummonerDto other = (RiotSummonerDto) obj;

	    return Objects.equals(puuid, other.puuid);
	}
	
}
