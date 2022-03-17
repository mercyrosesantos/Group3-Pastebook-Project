import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { Post } from '@models/post';
import { PostService } from '@services/post.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  posts: Post[] = [];
  postId?: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postService: PostService
  ) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.postId = Number(params['postId']);
      this.postService.getPost(this.postId).subscribe((response: Post[]) => {
        this.posts = response;
      });
    });

  }

}
