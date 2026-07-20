package com.vivascruz.nexotracker.summoner.dto;

import java.util.Objects;

/**
 * Represents the response returned to clients containing player profile information.
 */
public class SummonerDTO {
	
	/**
	 * Player unique identifier
	 */
	private String puuid;
	/**
	 * Integer that represents the icon which is used by the player in game
	 */
	private int profileIconId;
	/**
	 * Date summoner was last modified specified as epoch milliseconds
	 */
	private long revisionDate;
	/**
	 * Summoner level associated with the summoner
	 */
	private long summonerLevel;
	
	public SummonerDTO() {
	}

	public SummonerDTO(int profileIconID, long revisionDate, long summonerLevel) {
		this.profileIconId = profileIconID;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
	}
	
	

	public SummonerDTO(String puuid, int profileIconId, long revisionDate, long summonerLevel) {
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

	public int getProfileIconId() {
		return profileIconId;
	}

	public void setProfileIconId(int profileIconId) {
		this.profileIconId = profileIconId;
	}

	public long getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(long revisionDate) {
		this.revisionDate = revisionDate;
	}

	public long getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(long summonerLevel) {
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

	    SummonerDTO other = (SummonerDTO) obj;

	    return Objects.equals(puuid, other.puuid);
	}
	
}
