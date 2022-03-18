import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Friendrequest} from '@models/friendrequest';
import { SessionService } from './session.service';
import { Observable } from 'rxjs';
import { User } from '@models/user';
import { Friendship } from '@models/friendship';

@Injectable({
  providedIn: 'root'
})
export class FriendRequestService {

  private baseUrl: string = environment.apiUrl + '/friendrequests';
  private friendRequestUrl: string = environment.apiUrl + '/friendrequests/'
  private friendshipUrl: string = environment.apiUrl + '/friendship/'
  private friendslistUrl: string = environment.apiUrl + '/friendslist/'

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  // Create/update friend request
  createFriendRequest( friendRequest: Friendrequest): Observable<Object> {
    return this.http.post(this.friendRequestUrl,friendRequest,{responseType: 'text',  headers : this.sessionService.getHeaders()});
} 
  // Get friendship and if there's friend request sent
  getFriendship( friendId: number): Observable<Object> {
    return this.http.get<Friendship>(this.friendshipUrl + this.sessionService.getUserId() +'/'+ friendId, {headers : this.sessionService.getHeaders()});
} 
  //Get friend request status
  getFriendRequest( friendId: number): Observable<Object> {
  return this.http.get<Friendrequest>(this.friendRequestUrl + this.sessionService.getUserId() +'/'+ friendId, {headers : this.sessionService.getHeaders()});
} 

  //Get all friends
  getFriends(userId:Number): Observable<User[]>{
    return this.http.get<User[] >(this.friendslistUrl + this.sessionService.getUserId(), {headers : this.sessionService.getHeaders()});
  }

 
}