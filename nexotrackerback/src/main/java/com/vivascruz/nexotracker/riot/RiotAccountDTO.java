package com.vivascruz.nexotracker.riot;

public class RiotAccountDTO {

    private String puuid;
    private String gameName;
    private String tagLine;

    public RiotAccountDTO() {
	}
	public RiotAccountDTO(String puuid, String gameName, String tagLine) {
		super();
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
    
}