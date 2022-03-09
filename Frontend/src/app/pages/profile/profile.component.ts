import { Component, OnInit } from '@angular/core';
import { User } from '@models/user';
import { Post } from 'src/app/models/post';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  posts: Post[] = [];
    user: User = new User();
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
    this.profileService.getUserTimeline().subscribe((response: Post[]) => {
      this.posts = response;
    })
  }

  // Get User Profile
  getUserProfile() {
    this.profileService.getUserProfile().subscribe((response: User) => {
      this.user = response;
    })
  }

}
