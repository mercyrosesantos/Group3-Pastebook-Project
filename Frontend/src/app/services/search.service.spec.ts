import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { SearchService } from './search.service';
import { HttpClient } from '@angular/common/http';

describe('SearchService', () => {
  let searchService: SearchService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SearchService],
    });
    searchService = TestBed.inject(SearchService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(searchService).toBeDefined();
  });

  it('#searchAll should search a profile', () => {
    const testData = true;
    const keyword = 'admin';

    searchService
    .searchAll(keyword)
    .subscribe((data) => expect(data).toEqual(testData));
    const req = httpController.expectOne('http://localhost:8080/api/search/admin');

    expect(req.request.method).toEqual('GET');

    req.flush(testData);
  });


});
