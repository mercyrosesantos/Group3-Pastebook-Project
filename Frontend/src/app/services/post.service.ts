import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient } from '@angular/common/http';

import { Post } from '@models/post';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl: string = environment.apiUrl + '/posts';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  add(post: Post): void { }

}
