export class SummonerDto {
  puuid: string;
  profileIconId: number;
  revisonDate: number;
  summonerLevel: number;

  constructor(puuide: string, profileIconId: number, revisonDate: number, summonerLevel: number) {
    this.puuid = puuide;
    this.profileIconId = profileIconId;
    this.revisonDate = revisonDate;
    this.summonerLevel = summonerLevel;
  }
}
