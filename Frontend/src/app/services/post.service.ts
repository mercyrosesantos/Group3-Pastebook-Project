import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Post } from '@models/post';
import { SessionService } from './session.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl: string = environment.apiUrl + '/posts';
  private createPostUrl: string = environment.apiUrl + '/posts/create'
  private feedUrl: string = environment.apiUrl + '/feed/'
  private httpHeaders: HttpHeaders = new HttpHeaders({
    'Authorization': `${this.sessionService.getToken()}`
  })

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  add(post: Post): Observable<Object> {
    return this.http.post(this.createPostUrl, post);
  }

  getFeed(userId: number): Observable<Post[]>{
    return this.http.get<Post[]>(`${this.feedUrl}${userId}`)
  }

}
