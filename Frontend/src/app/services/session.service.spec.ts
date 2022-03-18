import { EventEmitter, Output } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { HttpClient } from '@angular/common/http';
import { SessionService } from './session.service';
import { User } from '@models/user';

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
    const testData = true;
    const inputEmail = 'admin';
  });

  
  

});