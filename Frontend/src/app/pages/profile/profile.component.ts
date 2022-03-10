import { Component, OnInit } from '@angular/core';
import { User } from '@models/user';
import { Post } from 'src/app/models/post';
import { ProfileService } from 'src/app/services/profile.service';
import * as moment from 'moment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  posts: Post[] = [];
  user: User = new User();
  pageNo: number = 0;
  formattedBirthday?: string;
    constructor(
      private profileService: ProfileService
    ) {
      this.getPosts();
      this.getUserProfile();
    }

  ngOnInit(): void {
  }

  // Get Posts
  getPosts() {
    this.profileService.getUserTimeline(this.pageNo).subscribe((response: Post[]) => {
      this.posts = response;
    })
  }

  // Get User Profile
  getUserProfile() {
    this.profileService.getUserProfile().subscribe((response: User) => {
      this.user = response;
      this.formattedBirthday = moment(this.user.birthDay).format('MMMM DD, YYYY');
    })
  }
}
