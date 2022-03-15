import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '@models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = environment.apiUrl + '/users';
  private registerUrl: string = environment.apiUrl + '/users/register';
  private onlineFriendsUrl: string = environment.apiUrl + '/online/';

  constructor(
    private http: HttpClient
  ) { }

  login(email: string, password: string): Observable<Object> { 
    return this.http.post(this.baseUrl + '/login', {email, password});
  }

  // Create user
  register(user: User): Observable<Object> {
    return this.http.post(this.registerUrl, user);
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  // Get online friends
  getOnlineFriends(userId: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.onlineFriendsUrl}${userId}`);
  }

  // Get user by id
  getUser(userId: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${userId}`);
  }
  
}