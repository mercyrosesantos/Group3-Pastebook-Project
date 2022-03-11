import { Component, HostListener, OnInit } from '@angular/core';
import { User } from '@models/user';
import { Post } from 'src/app/models/post';
import { ProfileService } from 'src/app/services/profile.service';
import * as moment from 'moment';
import { Photo } from '@models/photo';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  posts: Post[] = [];
  user: User = new User();
  photo: Photo = new Photo();
  
  pageNo: number = 0;
  formattedBirthday?: string;
  isMaxed = false;
  isLoading = false;
  pageSize = 1;//10;
  photoSrc? : string;

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
    console.log("calling");
    this.profileService.getUserTimeline(this.pageNo).subscribe((response: Post[]) => {
      this.posts = this.posts.concat(response);
      console.log('this.posts: ' + JSON.stringify(this.posts));
      this.isLoading = false;
      if (response.length <this.pageSize) {
        this.isMaxed = true;
      }
    })
  }

  // Get User Profile
  getUserProfile() {
    this.profileService.getUserProfile().subscribe((response: User) => {
      this.user = response;
      this.formattedBirthday = moment(this.user.birthDay).format('MMMM DD, YYYY');
      this.photoSrc = "data:image/png;base64," + this.user.photo?.image;
    })
  }
  @HostListener('window:scroll', ['$event']) // for window scroll events
  onScroll(event:any) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    // pos/max will give you the distance between scroll bottom and and bottom of screen in percentage.
    if(pos == max && !this.isLoading && !this.isMaxed)   {
      console.log('position is maxed');
      this.pageNo++;
      this.isLoading = true;
      this.getPosts();
    }


  }

}
