import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class NexotrackerApiService {

  private apiUrl = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) {}


  getSummoner(name: string, tag: string, server: string){

    const params = new HttpParams()
      .set('name', name)
      .set('tag', tag)
      .set('server', server);


    return this.http.post(
      `${this.apiUrl}/summoner/getSummoner`,
      null,
      {
        params,
        responseType: 'json'
      }
    );

  }

}
