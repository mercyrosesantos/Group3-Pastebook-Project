import { Component, Input, Output,EventEmitter, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Friendrequest } from '@models/friendrequest';
import { FriendRequestService } from '@services/friendrequest.service';
import { SessionService } from '@services/session.service';
import { User } from '@models/user';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-add-friend-button',
  templateUrl: './add-friend-button.component.html',
  styleUrls: ['./add-friend-button.component.css']
})
export class AddFriendButtonComponent implements OnInit {

  requestTimeStamp: Date = new Date();
  status: string = "pending";
  requestorId: number = Number(this.sessionService.getUserId);
  requesteeId: number = Number(this.activatedRoute.snapshot.params['id'])
  currentUrl: string = this.router.url;
 
  constructor(
    private friendRequestService: FriendRequestService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private sessionService: SessionService
  ) { 
    sessionService.hasToken.subscribe(hasToken => {
      this.requestorId = this.sessionService.getUserId();
    })
  }

  ngOnInit(): void{
  }

  onSubmit(){
    console.log(this.requestTimeStamp);
    console.log(this.status);
    console.log(this.requestorId);
    console.log(this.requesteeId);

    let friendRequest = new Friendrequest();
      friendRequest.requestTimeStamp = this.requestTimeStamp;
      friendRequest.requestorId = this.requestorId;
      friendRequest.status = this.status;
      friendRequest.requesteeId = this.requesteeId;
      
      this.createFriendRequest(friendRequest);

  

    
    
    // this.router.navigate([this.currentUrl]);


  }

  createFriendRequest(friendRequest: Friendrequest){
    this.friendRequestService.createFriendRequest(friendRequest).subscribe((response: Object)=>
    {console.log(response);
    });
  }

}