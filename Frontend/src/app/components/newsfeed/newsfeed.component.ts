import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  dataRefresher?: any;

  constructor(
    private postService: PostService,
    private sessionService: SessionService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadFeed();
    this.refreshData();
  }

  loadFeed() {
    this.postService.getFeed(this.loggedInUser).subscribe((response: Post[]) => {
      this.posts = response;
      this.posts.reverse();
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
 }

  refreshData() {
    this.dataRefresher =
    setInterval(() => {
      if (this.sessionService.getToken() == null) {
        return;
      }
      this.loadFeed();
    }, 60000); 
  }
}
