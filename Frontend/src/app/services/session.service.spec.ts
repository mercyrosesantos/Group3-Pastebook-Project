import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { SessionService } from './session.service';
import { HttpClient } from '@angular/common/http';

describe('SessionService', () => {
  let sessionService: SessionService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SessionService],
    });
    sessionService = TestBed.inject(SessionService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(sessionService).toBeDefined();
  });

});