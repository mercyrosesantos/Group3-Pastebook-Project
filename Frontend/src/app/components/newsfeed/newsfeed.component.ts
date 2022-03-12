import { Component, OnInit } from '@angular/core';

import { Post } from '@models/post';
import { PostService } from '@services/post.service';
import { SessionService } from '@services/session.service';

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.css']
})
export class NewsfeedComponent implements OnInit {

  posts: Post[] = [];
  loggedInUser: number = this.sessionService.getUserId();

  constructor(
    private postService: PostService,
    private sessionService: SessionService
  ) { }

  ngOnInit(): void {
    
    this.postService.getFeed(this.loggedInUser).subscribe((response: Post[]) => {
      this.posts = response;
      this.posts.reverse();
      console.log(this.posts);
    });
      
  }


}
