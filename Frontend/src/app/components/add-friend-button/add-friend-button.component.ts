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

  @Input()
  public friendRequest?: Friendrequest;

  @Input()
  public friendId? : number;

  @Input()
  public friendshipStatus? : string = "strangers";

  @Input()
  public callBack? : Function;

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

  onAdd(){
    console.log('onAdd');
    this.friendRequest = new Friendrequest();
    this.friendRequest.requestor = new User();
    this.friendRequest.requestor.id = this.sessionService.getUserId();
    this.friendRequest.requestee = new User();
    this.friendRequest.requestee.id = this.friendId;
    this.friendRequest.status = 'pending';
    this.friendRequestService.createFriendRequest(this.friendRequest).subscribe((response: any)=> {
      this.callBack!();
    });
  }
  onAccept(){
    console.log('onAccept');
    this.friendRequest!.status! = "accepted";
    this.friendRequestService.createFriendRequest(this.friendRequest!).subscribe((response: any)=> {
      this.callBack!();
    });
  }
  onReject(){
  console.log('onReject');
  this.friendRequest!.status! = "rejected";
  this.friendRequestService.createFriendRequest(this.friendRequest!).subscribe((response: any)=> {
    this.callBack!();
  });
}


}