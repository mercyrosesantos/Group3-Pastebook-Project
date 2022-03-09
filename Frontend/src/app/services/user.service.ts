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

  constructor(
    private http: HttpClient
  ) { }

  login(email: string, password: string): Observable<Object> { 
    return this.http.post(this.baseUrl + '/login', {email, password});
  }

  register(user: User): void { }

}