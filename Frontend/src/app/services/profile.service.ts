import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseUrl: string = environment.apiUrl + '/timeline/1';
  
  constructor(
    private http: HttpClient,
    // private sessionService: SessionService
  ) { }

  get(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }
}
