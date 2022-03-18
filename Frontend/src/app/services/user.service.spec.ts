import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { UserService } from './user.service';
import { HttpClient } from '@angular/common/http';
import { User } from '@models/user';
import { environment } from '@environments/environment';


describe('UserService', () => {
  let userService: UserService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService],
    });
    userService = TestBed.inject(UserService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpController.verify();
  });

  it('should be created', () => {
    expect(userService).toBeDefined();
  });

  it('login api', () => {
    const testData = true;
    const inputEmail = 'admin';
    const inputPassword = 'admin';
    

    userService
      .login(inputEmail, inputPassword)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne('http://localhost:8080/api/users/login');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('register api', () => {
    const testData = true;
    const user: User = new User();
    
    

    userService
      .register(user)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne('http://localhost:8080/api/users/register');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('#getOnlineFriends should show friends with active status', () => {
    let userId = 1;
    const testData = [{
      userId: 2,
      firstName: 'Kim',
      lastName: 'Possible'
    }]
    

    userService
      .getOnlineFriends(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/online/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getAll should show all friends', () => {
    let userId = 1;
    const testData = [{
      userId: 2,
      firstName: 'Kim',
      lastName: 'Possible'
    }]
    

    userService
      .getAll()
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/users');

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getOnlineFriends should show friends with active status', () => {
    let userId = 1;
    const testData = new User();
    

    userService
      .getUser(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/users/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });



});
