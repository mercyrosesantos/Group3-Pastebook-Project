import { fakeAsync, TestBed, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { FriendRequestService } from './friendrequest.service';
import { HttpClient } from '@angular/common/http';
import { SessionService } from './session.service';

describe('FriendRequestService', () => {
  let friendRequestService: FriendRequestService;
  let sessionService: SessionService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FriendRequestService],
    });
    friendRequestService = TestBed.inject(FriendRequestService);
    sessionService = TestBed.inject(SessionService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    const testData = true;
    const inputEmail = 'admin';
  });
  

  it('#createFriendRequest should create a friend request', () => {
    const testData = 'Success';
    const friendRequest = {
      id: 1,
      requestTimeStamp: new Date,
      status: 'pending',
      requestorId: 1,
      requesteeId: 2
    };
    
    friendRequestService
      .createFriendRequest(friendRequest)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne('http://localhost:8080/api/friendrequests/');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('#getFriendRequest should show friend request', () => {
    const testData = 'Status';
    
    const friendId = 2;
    
    friendRequestService
      .getFriendRequest(friendId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne('http://localhost:8080/api/friendrequests/' + null + '/' + friendId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getFriendship should show friendship status', () => {
    let userId = 1;
    let userIdP = null;
    const testData = 'Friends';
    const friendId = 2

    friendRequestService
      .getFriendship(friendId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne('http://localhost:8080/api/friendship/' + userIdP + '/' + friendId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getFriends should show friends', () => {
    let userId = 1;
    let userIdP = null;
    const testData = [
      {firstName: 'John',
      lastName: 'Doe'
    }, 
    
    {firstName: 'Jay',
    lastName: 'Cruz'
  }
    ];

    friendRequestService
      .getFriends(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne('http://localhost:8080/api/friendslist/' + userIdP);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

});
