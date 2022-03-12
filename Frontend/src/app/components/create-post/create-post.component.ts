import { Component, Input, Output,EventEmitter,OnInit, ViewChild } from '@angular/core';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Post } from '@models/post';
import { PostService } from '@services/post.service';
import { SessionService } from '@services/session.service';
import { User } from '@models/user';
import { NgForm } from '@angular/forms';

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

  @Input() 
  timeline!: string;
  
  @Input()
  public whenPost?: Function;

  @ViewChild('myForm', { static: false })
  myForm!: NgForm;

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

    let user : User = new User();
    user.id = this.userId;

    post.user = user;
    post.postTimestamp = this.postTime;
    post.timelineUserId = parseInt(this.timeline);
    
    let timeline: User = new User();
    timeline.id = parseInt(this.timeline);
    post.timelineUser = timeline;
    
    this.addPost(post);
    this.myForm.resetForm();
    this.router.navigate([this.currentUrl]);
  }

  addPost(post: Post){
    this.postService.add(post).subscribe((response: Object) => {
      console.log(response);
      this.whenPost!();
    });
  }
}
