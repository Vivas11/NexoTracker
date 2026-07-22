import { Routes } from '@angular/router';

import { Home } from './features/home/pages/home/home';
import { Profile } from './features/summoner/pages/profile/profile';
import { MatchDetails } from './features/match/pages/match-details/match-details';
import { ChampionPage } from './features/champion/pages/champion-page/champion-page';
import { Rankings } from './features/rankings/pages/rankings/rankings';
import { NotFound } from './features/not-found/not-found';

export const routes: Routes = [

  {
    path: '',
    component: Home
  },

  {
    path: 'profile/:server/:gameName/:tag',
    component: Profile
  },

  {
    path: 'match/:matchId',
    component: MatchDetails
  },

  {
    path: 'champion/:championName',
    component: ChampionPage
  },

  {
    path: 'rankings',
    component: Rankings
  },

  {
    path: '**',
    component: NotFound
  }

];
