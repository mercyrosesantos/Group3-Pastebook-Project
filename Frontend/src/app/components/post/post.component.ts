import { Component, OnInit, Input } from '@angular/core';
import { Post } from '@models/post';
import { ProfileService } from '@services/profile.service';
import { ReactionService } from '@services/reaction.service';
import { Reaction } from '@models/reaction';
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post!: Post;
  comments?: Reaction[];
  currentComment?: string;

  constructor(
    private profileService: ProfileService,
    private reactionService: ReactionService
  ) { 
    
  }

  ngOnInit(): void {
    this.getCommentsFromPost();
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

    console.log("test")
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
}
