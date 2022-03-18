import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { ReactionService } from './reaction.service';
import { HttpClient } from '@angular/common/http';
import { Reaction } from '@models/reaction';
import { environment } from '@environments/environment';

describe('ReactionService', () => {
  let reactionService: ReactionService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReactionService],
    });
    reactionService = TestBed.inject(ReactionService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(reactionService).toBeDefined();
  });

  it('#getCommentsByPost should show comments in a post', () => {
    const testData: string | number | boolean | any | null = [];
    
    const postId = 2;
    
    reactionService
      .getCommentsByPost(postId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/comments/'+ postId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getLikesByPost should show likes in a post', () => {
    const testData: string | number | boolean | any | null = [];
    
    const postId = 2;
    
    reactionService
      .getLikesByPost(postId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/likes/'+ postId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

});
