import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { Friendship } from '@models/friendship';
import { FriendRequestService } from '@services/friendrequest.service';
import { ProfileService } from '@services/profile.service';
import { SessionService } from '@services/session.service';
import { User } from '@models/user';
import { UserService } from '@services/user.service';



@Component({
  selector: 'app-friendslist',
  templateUrl: './friendslist.component.html',
  styleUrls: ['./friendslist.component.css']
})
export class FriendslistComponent implements OnInit {

  user?: User[];
  friend: any;
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
      this.userId = params['userId'];
      this.friendRequestService.getFriends(this.userId).subscribe((response: User) => {
        this.friend = response;
      });
      console.log(this.friend);
    })
  }

  linkToProfile(){
    this.activatedRoute.params.subscribe(params => {
      this.finder = Number(params['id']);
      this.profileService.getUserProfile(this.finder);
    })
  }

}
