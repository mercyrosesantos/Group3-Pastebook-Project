import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Reaction } from '@models/reaction';
import { User } from '@models/user';


@Injectable({
  providedIn: 'root'
})
export class ReactionService {

  private commentsByPostUrl: string = environment.apiUrl + '/comments/';
  private likesByPostUrl: string = environment.apiUrl + '/likes/';

  constructor(
    private http: HttpClient
  ) { }

  // Get Comments by Post
  getCommentsByPost(postId?: number): Observable<Reaction[]> {
    return this.http.get<Reaction[]>(this.commentsByPostUrl+postId);
  }

  // Get Likes by Post
  getLikesByPost(postId?: number): Observable<User[]> {
    return this.http.get<User[]>(this.likesByPostUrl+postId);
  }
  
}
