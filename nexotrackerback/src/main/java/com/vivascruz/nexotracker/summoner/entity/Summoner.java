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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "puuid")
    private String puuid;
    
    @Column(nullable = false, name = "profileIconID")
    private int profileIconID;

    @Column(nullable = false, name = "revisionDate")
    private long revisionDate;

    @Column(nullable = false, name = "summonerLevel")
	private long summonerLevel;
    
    public Summoner() {
	}

	public Summoner(String puuid, int profileIconID, long revisionDate, long summonerLevel) {
		super();
		this.puuid = puuid;
		this.profileIconID = profileIconID;
		this.revisionDate = revisionDate;
		this.summonerLevel = summonerLevel;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

	public int getProfileIconID() {
		return profileIconID;
	}

	public void setProfileIconID(int profileIconID) {
		this.profileIconID = profileIconID;
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
