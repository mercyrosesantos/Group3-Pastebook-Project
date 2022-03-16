import { Component, HostListener, OnInit } from '@angular/core';

import { User } from '@models/user';
import { Post } from '@models/post';
import { ProfileService } from 'src/app/services/profile.service';
import * as moment from 'moment';
import { Photo } from '@models/photo';
import {ActivatedRoute} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { SessionService } from '@services/session.service';
import { FriendRequestService } from '@services/friendrequest.service';
import { Friendship } from '@models/friendship';
import { Friendrequest } from '@models/friendrequest';
import { PhotoService } from '@services/photo.service';
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
  friendshipStatus?: string;
  friendRequest?: Friendrequest;
  newImageFile? : FileList;
  uploadedNewImage: boolean = false;
  constructor(
    private profileService: ProfileService,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private sessionService: SessionService,
    private friendRequestService : FriendRequestService,
    private photoService: PhotoService
  ) {
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.user.url = params['id'];
      this.getUserProfile();
    })


  }

  //Refresh timeline
  public loadPage() {
    if (this.route) {
      this.user.url = this.route.snapshot.params['id'];
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
  addFriendCallBack() {
    console.log('addFriendCallBack');
    this.getFriendship();
  }
  // Get User Profile
  getUserProfile() {
    this.profileService.getUserProfileByUrl(this.user.url!).subscribe((response: User) => {
      this.user = response;
      this.formattedBirthday = moment(this.user.birthDay).format('MMMM DD, YYYY');
      if (this.user.photo?.image != undefined) {
        this.photoSrc = "data:image/png;base64," + this.user.photo?.image;
      } else {
        this.photoSrc="./assets/DefaultProfilePicture.jpg";
      }
      console.log('photoSrc: ' + this.photoSrc);
      this.isOwnProfile = this.user.id == this.sessionService.getUserId();
      if (this.isOwnProfile) {
        this.friendshipStatus = 'own';
      } else {
        this.getFriendship();
      }
      this.loadPage();


    })
  }
  getFriendship() {
    this.friendRequestService.getFriendship(this.user.id!).subscribe((response: Friendship) => {
      this.friendshipStatus = response.status;
      console.log('status: ' + this.friendshipStatus);
      if (this.friendshipStatus == 'pending') {
        this.friendRequestService.getFriendRequest(this.user.id!).subscribe((response: Friendrequest) => {
          this.friendRequest = response;
        });
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
  changeProfilePic(event: any) {
    const element = event.currentTarget as HTMLInputElement;
    this.newImageFile = element.files!;
    if (this.newImageFile) {
      let fileReader = new FileReader();
      let self = this;
        fileReader.onload = function () {
          self.photoSrc = fileReader.result?.toString();
        }
        fileReader.readAsDataURL(this.newImageFile[0]);
      console.log("FileUpload -> files", this.newImageFile);
      this.uploadedNewImage = true;
    }

  }
  uploadImage() {
    if (this.uploadedNewImage) {
      var data = new FormData();
      // data.append('file',this.photoSrc!);
      data.append('file', this.newImageFile![0], this.newImageFile![0].name);

      data.append('userId', this.sessionService.getUserId());
      this.photoService.uploadPhoto(data)
      .subscribe((response: Object) => {
        console.log('success uploading');
        this.getUserProfile();
        this.uploadedNewImage = false;
        this.modalService.dismissAll();
      })
    }
    

  }
}
