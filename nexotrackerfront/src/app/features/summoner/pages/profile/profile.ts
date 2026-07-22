import {Component, inject, signal} from '@angular/core';
import {SummonerService} from '../../services/summoner_service';
import {ActivatedRoute} from '@angular/router';
import {SummonerDto} from '../../model/summoner-dto';

@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.html',
})
export class Profile {

  summoner = signal<SummonerDto | undefined>(undefined);

  private route = inject(ActivatedRoute);

  private summonerService = inject(SummonerService);

  ngOnInit() {
    const gameName = this.route.snapshot.paramMap.get('gameName');
    const tagLine = this.route.snapshot.paramMap.get('tag');
    const server = this.route.snapshot.paramMap.get('server');

    console.log("PARAMETROS");
    console.log(gameName);
    console.log(tagLine);
    console.log(server);

    if (gameName != null && tagLine != null && server != null) {
      this.summonerService
        .getSummoner(gameName, tagLine, server)
        .subscribe({
          next: (data) => {
            this.summoner.set(data);
          },
          error: (err) => {
            console.error(err);
          }
        });
    }
  }

}
