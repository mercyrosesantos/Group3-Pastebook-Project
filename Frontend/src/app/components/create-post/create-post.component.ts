import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

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
  userId: number = Number(this.sessionService.getUserId());
  postTime: Date = new Date();
  currentUrl: string = this.router.url;

  constructor(
    private postService: PostService,
    private router: Router,
    private route: ActivatedRoute,
    private sessionService: SessionService
  ) {

    sessionService.hasToken.subscribe(hasToken => {
      this.userId = this.sessionService.getUserId();
    })

  }

  ngOnInit(): void {
  }

  onSubmit() {

    console.log(this.content);
    console.log(this.userId);

    let post = new Post();

      post.content = this.content;
      post.userId = this.userId;
      post.postTimestamp = this.postTime;

      if (this.currentUrl='') {
        post.timelineUserId = this.userId;
      } else {
        post.timelineUserId = Number(this.route.snapshot.params['id']);
      }

      console.log(post.userId);
      console.log(post.timelineUserId);
      console.log(typeof(post.userId));
      console.log(typeof(post.timelineUserId));

      this.addPost(post);
  }

  addPost(post: Post){
    this.postService.add(post).subscribe((response: Object) => {
      console.log(response);
    });

  }

}
