package com.vivascruz.nexotracker.summoner.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "summoner")
public class Summoner {

	private static final long serialVersionUID = 1L;

	@Id
	private String puuid;

	@Column(name = "profile_icon_id")
	private Integer profileIconId;

	@Column(name = "revision_date")
	private Long revisionDate;

	@Column(name = "summoner_level")
	private Long summonerLevel;

	public Summoner() {
	}

	public Summoner(String puuid, int profileIconId, long revisionDate, long summonerLevel) {
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

		Summoner other = (Summoner) obj;

		return Objects.equals(puuid, other.puuid);
	}

}
