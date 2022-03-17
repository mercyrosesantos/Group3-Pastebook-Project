import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SessionService } from './session.service';
import { UserService } from './user.service';
import { User } from '@models/user';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private baseUrl: string = environment.apiUrl + '/search/';
  private httpHeaders: HttpHeaders = new HttpHeaders({
    'Authorization': `${this.sessionService.getToken()}`
  })

  constructor(
    private http: HttpClient,
    private sessionService: SessionService,
    private userService: UserService
  ) { }

  searchAll(searchTerm: string): Observable<Object> { 
    return this.http.get(`${this.baseUrl}${searchTerm}`, {headers : this.sessionService.getHeaders()});
  }

}
