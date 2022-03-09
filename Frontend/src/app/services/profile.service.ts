import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Post } from '@models/post';
import { User } from '@models/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl: string = environment.apiUrl + '/timeline/1';
  private profileUrl: string = environment.apiUrl + '/profile/1';

  constructor(
    private http: HttpClient,
    // private sessionService: SessionService
  ) { }

  // Get Posts
  getUserTimeline(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }

  // Get User Profile
  getUserProfile(): Observable<User> {
    return this.http.get<User>(this.profileUrl);
  }
}
