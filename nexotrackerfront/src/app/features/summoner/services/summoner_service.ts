import { Injectable, inject } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SummonerDto } from '../model/summoner-dto';

@Injectable({
  providedIn: 'root'
})
export class SummonerService {

  private http = inject(HttpClient);

  private api = environment.apiUrl;

  getSummoner(
    name: string,
    tag: string,
    server: string
  ): Observable<SummonerDto> {

    return this.http.get<SummonerDto>(
      `${this.api}/summoner/getSummonerByTag`,
      {
        params: {
          name,
          tag,
          server
        }
      }
    );
  }
}
