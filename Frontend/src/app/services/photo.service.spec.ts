import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { PhotoService } from './photo.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '@environments/environment';

describe('PhotoService', () => {
  let photoService: PhotoService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PhotoService],
    });
    photoService = TestBed.inject(PhotoService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(photoService).toBeDefined();
  });


  it('#getPhoto should set default Photo', () => {
    const testData = {};

    photoService
      .getPhoto()
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/photo/3');

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#uploadPhoto should set user Photo', () => {
    const testData = 'Uploaded';
    const uploadPhoto = new FormData();
    photoService
      .uploadPhoto(uploadPhoto)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/user/photos/');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

});
