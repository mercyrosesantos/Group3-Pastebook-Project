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
  loggedInUser?: number;
  constructor(
    private postService: PostService,
    private sessionService: SessionService
  ) { }

  ngOnInit(): void {
    this.loggedInUser = this.sessionService.getUserId();
  }


}
