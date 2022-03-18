import { Component, OnInit, Input } from '@angular/core';
import * as moment from 'moment';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { LikeInformation } from '@models/like-information';
import { Post } from '@models/post';
import { ProfileService } from '@services/profile.service';
import { ReactionService } from '@services/reaction.service';
import { Reaction } from '@models/reaction';
import { SessionService } from '@services/session.service';
import { User } from '@models/user';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';




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
    private modalService: NgbModal,
    private router: Router
  ) { 
    
  }

  ngOnInit(): void {
    this.getCommentsFromPost();
    this.getLikesFromPost();
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
    this.profileService.createReaction(likeReaction).subscribe((response: Object) => {
      this.getLikesFromPost();
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
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
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }

  //Get Comments from Post
  getCommentsFromPost() {
    this.reactionService.getCommentsByPost(this.post.id).subscribe((response: Reaction[]) => {
      this.comments = response;
      for (let comment of this.comments) {
        comment.reactionTimeString = moment(comment?.reactionTimestamp).format('MMMM DD, YYYY HH:mm:ss');
      }
      this.formattedPostTimeStamp = moment(this.post.postTimestamp).format('MMMM DD, YYYY HH:mm:ss');
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
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
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }
  
  //Show users who liked the post
  formatLikers() {
    return JSON.stringify(this.likeInformation?.users);
  }

   //Modal - to show who reacted on the post
   open(content?: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {});
  }
}
