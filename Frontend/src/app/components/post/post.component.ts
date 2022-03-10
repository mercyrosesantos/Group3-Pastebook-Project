import { Component, OnInit, Input } from '@angular/core';
import { Post } from '@models/post';
import { ProfileService } from '@services/profile.service';
import { ReactionService } from '@services/reaction.service';
import { Reaction } from '@models/reaction';
import { LikeInformation } from '@models/like-information';
import { User } from '@models/user';
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post!: Post;
  comments?: Reaction[];
  currentComment?: string;

  likeInformation?: LikeInformation = new LikeInformation();

  constructor(
    private profileService: ProfileService,
    private reactionService: ReactionService
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
          "id": 1
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
          "id": 1
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
      
    });
  }

  //Get Likes from Post
  getLikesFromPost() {
    this.reactionService.getLikesByPost(this.post.id).subscribe((response: User[]) => {
      this.likeInformation = new LikeInformation();
      this.likeInformation.like = response.length;
      this.likeInformation.users = response;
      this.likeInformation.likeIds = new Set<number>();
      for (let user of response) {
        if (user?.id != undefined) {
          this.likeInformation.likeIds.add(user?.id!);
        }
      }
    });
  }

  //To check if the user liked the post
  isCurrentUserLiked(): boolean {
    let isCurrentlyLiked = false;
    isCurrentlyLiked = this.likeInformation?.likeIds?.has(1)!;
    return isCurrentlyLiked;
  }

  
  //Show users who liked the post(not in use yet)
  formatLikers() {
    return JSON.stringify(this.likeInformation?.users);
  }
}
