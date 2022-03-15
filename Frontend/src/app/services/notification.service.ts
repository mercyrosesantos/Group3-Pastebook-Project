import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private baseUrl: string = environment.apiUrl + '/notifications';
  private unreadUrl: string = this.baseUrl + '/unread';
  private readUrl: string = this.baseUrl + '/read'

  constructor(
    private http: HttpClient
  ) { }

  // Get all user notifications
  getNotif(userId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${userId}`);
  }

  // Get unread notifications count
  getUnread(userId: number): Observable<Object> {
    return this.http.get(`${this.getUnread}/${userId}`);
  }

  // Set notifications as read
  setAsRead(userId: number): Observable<Object> {
    return this.http.get(`${this.readUrl}/${userId}`);
  }

}
