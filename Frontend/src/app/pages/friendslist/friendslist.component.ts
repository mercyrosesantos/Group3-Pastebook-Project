import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { FriendRequestService } from '@services/friendrequest.service';
import { ProfileService } from '@services/profile.service';
import { SessionService } from '@services/session.service';
import { User } from '@models/user';
import { UserService } from '@services/user.service';
import { HttpErrorResponse } from '@angular/common/http';



@Component({
  selector: 'app-friendslist',
  templateUrl: './friendslist.component.html',
  styleUrls: ['./friendslist.component.css']
})
export class FriendslistComponent implements OnInit {

  friends: User[] = [];
  userId: number = this.sessionService.getUserId();
  finder?: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private friendRequestService: FriendRequestService,
    private profileService: ProfileService,
    private router: Router,
    private sessionService: SessionService,
    private userService: UserService
  ) { }

  ngOnInit(): void{
    this.activatedRoute.params.subscribe(params => {
      this.friendRequestService.getFriends(this.sessionService.getUserId()).subscribe((response: any) => {
        this.friends = response['body'];
      },
      (error: HttpErrorResponse) => {
          if (error.status !== 401) {
              return;
          }
          this.sessionService.clear();
          this.router.navigate(['/login']);
      });
    })
  }
}
