package com.vivascruz.nexotracker.riot;

public class RiotRegion {

    private String region;
    private String platform;

    public RiotRegion(String region, String platform) {
        this.region = region;
        this.platform = platform;
    }

    public String getRegion() {
        return region;
    }

    public String getPlatform() {
        return platform;
    }
}