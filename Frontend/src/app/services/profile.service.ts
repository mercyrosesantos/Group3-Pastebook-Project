import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Post } from '@models/post';
import { User } from '@models/user';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl: string = environment.apiUrl + '/timeline/' + this.sessionService.getUserId();
  private profileUrl: string = environment.apiUrl + '/profile/' + this.sessionService.getUserId();
  private reactionUrl: string = environment.apiUrl + '/reactions';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  // Get Posts
  getUserTimeline(pageNo: number): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl + '/' + pageNo);
  }

  // Get User Profile
  getUserProfile(): Observable<User> {
    return this.http.get<User>(this.profileUrl);
  }

  // Create Reactions
  createReaction(reaction: any): Observable<Object> {
    return this.http.post(this.reactionUrl,reaction,{responseType: 'text'});
  }
}
