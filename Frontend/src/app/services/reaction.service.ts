import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Post } from '@models/post';
import { Reaction } from '@models/reaction';


@Injectable({
  providedIn: 'root'
})
export class ReactionService {

  private commentsByPostUrl: string = environment.apiUrl + '/comments/';

  constructor(
    private http: HttpClient
  ) { }

  // Get Comments by Post
  getCommentsByPost(postId?: number): Observable<Reaction[]> {
    return this.http.get<Reaction[]>(this.commentsByPostUrl+postId);
  }
}
