import { Component, HostListener, OnInit } from '@angular/core';

import { User } from '@models/user';
import { Post } from '@models/post';
import { ProfileService } from 'src/app/services/profile.service';
import * as moment from 'moment';
import { Photo } from '@models/photo';
import {ActivatedRoute} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { SessionService } from '@services/session.service';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  posts: Post[] = [];
  user: User = new User();
  photo: Photo = new Photo();
 
  formattedBirthday?: string;
  photoSrc? : string;

  pageNo: number = 0;
  isMaxed = false;
  isLoading = false;
  pageSize = 10;
  isOwnProfile: boolean = false;
  constructor(
    private profileService: ProfileService,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private sessionService: SessionService
  ) {
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.user.id = Number(params['id']);
      this.isOwnProfile = this.user.id == this.sessionService.getUserId();
      this.loadPage();
      this.getUserProfile();
    })


  }

  //Refresh timeline
  public loadPage() {
    if (this.route) {
      this.user.id = this.route.snapshot.params['id'];
    }
    this.posts = [];
    this.pageNo = 0;
    this.getPosts();
  }

  // Get Posts
  getPosts() {
    this.profileService.getUserTimeline(this.user.id!,this.pageNo).subscribe((response: Post[]) => {
      this.posts = this.posts.concat(response);
      this.isLoading = false;
      if (response.length <this.pageSize) {
        this.isMaxed = true;
      }
    })
  }

  // Get User Profile
  getUserProfile() {
    this.profileService.getUserProfile(this.user.id!).subscribe((response: User) => {
      this.user = response;
      this.formattedBirthday = moment(this.user.birthDay).format('MMMM DD, YYYY');
      if (this.user.photo?.image != undefined) {
        this.photoSrc = "data:image/png;base64," + this.user.photo?.image;
      }


    })
  }


  @HostListener('window:scroll', ['$event']) // for window scroll events
  onScroll(event:any) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    // pos/max will give you the distance between scroll bottom and and bottom of screen in percentage.
    
    if((pos + 10) >= max && !this.isLoading && !this.isMaxed)   {
      console.log('position is maxed');
      this.pageNo++;
      this.isLoading = true;
      this.getPosts();
    }
  }

  //Modal to upload profile picture
  open(content?: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {});
  }

  //Save About Me
  saveAboutMe(){
    this.profileService.updateAboutMe(this.user).subscribe((response: Object) => {
      this.modalService.dismissAll()
    })
  }
}
