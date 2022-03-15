import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { ProfileService } from '@services/profile.service';
import { SessionService } from '@services/session.service';
import { UserService } from '@services/user.service';
import { User } from '@models/user';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  friends?: User[];
  userId: number = this.sessionService.getUserId();
  friendId?: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private profileService: ProfileService,
    private userService: UserService
  ) { }

  ngOnInit(): void {

    this.userService.getOnlineFriends(this.userId).subscribe((response: User[]) => {
      this.friends = response;
    })

  }

  redirect() {
    this.route.params.subscribe(params => {
      this.friendId = Number(params['id']);
      this.profileService.getUserProfile(this.friendId);
    })
  }

}
