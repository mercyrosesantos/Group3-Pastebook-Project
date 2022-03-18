import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { PostService } from './post.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { User } from '@models/user';
import { Post } from '@models/post';

describe('PostService', () => {
  let postService: PostService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PostService],
    });
    postService = TestBed.inject(PostService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(postService).toBeDefined();
  });

  it('#add should add a post', () => {
    const testData = 'Success';
    const post = {
      id: 1,
      content: 'hello',
      postTimeStamp: new Date,
      isActive: true,
      user: new User(),
      timeline: new User()
    };
    
    postService
      .add(post)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/posts/create');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('#getFeed should show feed', () => {
    const testData = [
      {
        firstName: 'Jay',
        lastName: 'Cruz',
        content: "Hi"
      },
      {
        firstName: 'John',
        lastName: 'Meyer',
        content: "Hello"
      }
    ];
    
    const userId = 1;
    
    postService
      .getFeed(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/feed/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getPost should show a post', () => {
    const postId = 1;
    const testData = [{
      id: 1,
      content: 'hello',
      postTimeStamp: new Date,
      isActive: true,
      user: new User(),
      timeline: new User()
    }];
    postService
      .getPost(postId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/posts/' + postId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });


  
});
