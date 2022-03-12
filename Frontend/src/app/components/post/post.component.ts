import { Component, OnInit, Input } from '@angular/core';

import { Post } from '@models/post';
import { ProfileService } from '@services/profile.service';
import { ReactionService } from '@services/reaction.service';
import { Reaction } from '@models/reaction';
import { LikeInformation } from '@models/like-information';
import { User } from '@models/user';
import * as moment from 'moment';
import { SessionService } from '@services/session.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post!: Post;
  comments?: Reaction[];
  currentComment?: string;
  formattedPostTimeStamp?: string;
  isCurrentUserLiked: boolean = false;
  likeInformation?: LikeInformation = new LikeInformation();

  constructor(
    private profileService: ProfileService,
    private reactionService: ReactionService,
    private sessionService: SessionService,
    private modalService: NgbModal
  ) { 
    
  }
  open(content?: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      
    }, (reason) => {

    });
  }

  ngOnInit(): void {
    this.getCommentsFromPost();
    this.getLikesFromPost();
    // this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      
    // }, (reason) => {

    // });
  }

  //Create like Reaction
  createLikeReaction() {
    let likeReaction = {
      "reactionType":{
          "id": 1
      },
      "post":{
          "id": this.post.id
      },
      "user":{
          "id": this.sessionService.getUserId()
      } 
    }
    console.log("test")
    this.profileService.createReaction(likeReaction).subscribe((response: Object) => {
      this.getLikesFromPost();
    });
  }

  //Create Comment Reaction
  createCommentReaction() {
    let commentReaction = {
      "reactionType":{
          "id": 2
      },
      "post":{
          "id": this.post.id
      },
      "user":{
          "id": this.sessionService.getUserId()
      },
      "content": this.currentComment
    }
    this.profileService.createReaction(commentReaction).subscribe((response: Object) => {
      this.currentComment = '';
      this.getCommentsFromPost();
    });
  }

  //Get Comments from Post
  getCommentsFromPost() {
    this.reactionService.getCommentsByPost(this.post.id).subscribe((response: Reaction[]) => {
      this.comments = response;
      this.formattedPostTimeStamp = moment(this.post.postTimestamp).format('MMMM DD, YYYY HH:mm:ss');
    });
  }

  //Get Likes from Post
  getLikesFromPost() {
    this.reactionService.getLikesByPost(this.post.id).subscribe((response: User[]) => {
      this.likeInformation = new LikeInformation();
      this.likeInformation.like = response.length;
      this.likeInformation.users = response;
      for (let user of response) {
        this.likeInformation.likeIds.add(user.id!);
      }
      this.isCurrentUserLiked = this.likeInformation.likeIds.has(parseInt(this.sessionService.getUserId()));
    });
  }

  //To check if the user liked the post
  // isCurrentUserLiked(): boolean {
  //   let isCurrentlyLiked = false;
  //   isCurrentlyLiked = this.likeInformation?.likeIds?.has(this.sessionService.getUserId())!;
  //   console.log('isCurrentlyLiked');

  //   return isCurrentlyLiked;
  // }

  
  //Show users who liked the post(not in use yet)
  formatLikers() {
    return JSON.stringify(this.likeInformation?.users);
  }
}
