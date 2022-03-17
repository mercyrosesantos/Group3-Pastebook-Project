import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Notification } from '@models/notification';
import { NotificationService } from '@services/notification.service';
import { SessionService } from '@services/session.service';
import { User } from '@models/user';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  userId: number = Number(this.sessionService.getUserId());
  user?: User;
  unreadNotif?: number;
  notifList: any;
  notification?: Notification;

  dataRefresher?: any;

  constructor(
    private notificationService: NotificationService,
    private router: Router,
    private sessionService: SessionService
  ) { 
    sessionService.hasToken.subscribe(hasToken => {
      this.userId = this.sessionService.getUserId();
    })
  }

  ngOnInit(): void {
    this.refreshData();
  }

  getUserNotif(){
    this.notificationService.getNotif(this.userId).subscribe((response: Notification) => {
      this.notifList = response;
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }

  getUnreadNotif(){
    
    this.notificationService.getUnread(this.userId).subscribe((response: any) => {
      this.unreadNotif = response;
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    })
    return this.unreadNotif;
  }

  setNotifAsRead(){
    this.notificationService.setAsRead(this.userId).subscribe((response: any) => {},
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }

  loadNotif(){
    this.getUserNotif();
    this.getUnreadNotif();
  }

  refreshData() {
    
    this.dataRefresher =
    setInterval(() => {
      if (this.sessionService.getToken() == null) {
        return;
      }
      this.loadNotif();
    }, 10000); 
  }
}
