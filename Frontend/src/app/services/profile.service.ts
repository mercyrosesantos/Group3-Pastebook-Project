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
  private profileUrl: string = environment.apiUrl + '/profile/';
  private reactionUrl: string = environment.apiUrl + '/reactions';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  // Get Posts
  getUserTimeline(userId: number, pageNo: number): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl + userId + '/' + pageNo);
  }

  // Get User Profile
  getUserProfile(userId: number): Observable<User> {
    return this.http.get<User>(this.profileUrl +  userId );
  }

  // Create Reactions
  createReaction(reaction: any): Observable<Object> {
    return this.http.post(this.reactionUrl,reaction,{responseType: 'text'});
  }
}
