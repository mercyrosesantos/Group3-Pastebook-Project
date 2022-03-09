import { Component, OnInit, Input } from '@angular/core';
import { Post } from '@models/post';
import { ProfileService } from '@services/profile.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post!: Post;
  constructor(
    private profileService: ProfileService
  ) { 
    
  }

  ngOnInit(): void {
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
    this.profileService.createReaction(likeReaction);
  }
}
