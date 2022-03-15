import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Friendrequest} from '@models/friendrequest';
import { SessionService } from './session.service';
import { Observable } from 'rxjs';
import { User } from '@models/user';

@Injectable({
  providedIn: 'root'
})
export class FriendRequestService {

  private baseUrl: string = environment.apiUrl + '/friendrequests';
  private friendRequestUrl: string = environment.apiUrl + '/friendrequests/'
  private httpHeaders: HttpHeaders = new HttpHeaders({
    'Authorization': `${this.sessionService.getToken()}`
  })

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  // Create friend request
  createFriendRequest( friendRequest: Friendrequest): Observable<Object> {
      return this.http.post(this.friendRequestUrl,friendRequest);
  } 

 
}