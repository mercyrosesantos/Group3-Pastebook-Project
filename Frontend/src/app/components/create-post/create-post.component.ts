import { Component, OnInit } from '@angular/core';

import { Post } from '@models/post';
import { PostService } from '@services/post.service';
import { SessionService } from '@services/session.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  content: string = "";
  userId: number = this.sessionService.getUserId();
  postTime: Date = new Date();

  constructor(
    private postService: PostService,
    private sessionService: SessionService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {

    console.log(this.content);

    let post = new Post();

      post.content = this.content;
      post.userId = this.userId;
      post.postTimestamp = this.postTime;

      this.addPost(post);
  }

  addPost(post: Post){
    this.postService.add(post).subscribe((response: Object) => {
      console.log(response);
    });
  }

}
