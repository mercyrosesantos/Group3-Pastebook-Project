import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/post';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  posts: Post[] = [];
    constructor(
      private profileService: ProfileService
    ) {
      this.getPosts();
    }
   

  ngOnInit(): void {
  }

  getPosts() {
    this.profileService.get().subscribe((response: Post[]) => {
      this.posts = response;
    })
  }

}
