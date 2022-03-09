import { Component, OnInit, Input } from '@angular/core';

import { Post } from '@models/post';
import { PostService } from '@services/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  @Input() post!: Post;

  constructor(
    private postService: PostService
  ) { }

  ngOnInit(): void {
  }

  addPost(){
    this.postService.add(this.post);
  }

}
