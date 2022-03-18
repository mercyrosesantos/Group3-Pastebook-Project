import { fakeAsync, TestBed, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { AlbumService } from './album.service';
import { HttpClient } from '@angular/common/http';
import { SessionService } from './session.service';
import { environment } from '@environments/environment';
import { Album } from '@models/album';

describe('AlbumService', () => {
  let albumService: AlbumService;
  let sessionService: SessionService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AlbumService],
    });
    albumService = TestBed.inject(AlbumService);
    sessionService = TestBed.inject(SessionService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    const testData = true;
    const inputEmail = 'admin';
  });
  

  it('#getAlbumByUserId show album of the given id', () => {
    const testData: string | number | boolean | any | null = [];
    const userId = 2;
    albumService
      .getAlbumByUserId(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/album/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getAlbumshow show album of the given id', () => {
    const testData: string | number | boolean | any | null = [];
    const albumId = 2;
    albumService
      .getAlbum(albumId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/albums-view/'+ albumId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#updateAlbum should update an album', () => {
    const testData = 'Updated Album';
    const album = 'This album';
    const albumId = 2;
    albumService
      .updateAlbum(albumId, album)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/user/updateAlbum/');

      expect(req.request.method).toEqual('PUT');

      req.flush(testData);
  });

  it('#uploadPhotos should post a photo', () => {
    const testData = 'Successful upload';
    const photo = new FormData();

    albumService
      .uploadPhotos(photo)
      .subscribe((data: any) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/album/photos/');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('#createAlbumByUserId should create an Album', () => {
    const testData = 'Created album';
    const album = 'New album';
    const userId = 2;

    albumService
      .createAlbumByUserId(userId, album)
      .subscribe((data: any) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/user/albums/');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('#deleteAlbum should create an Album', () => {
    const testData = 'Deleted album';
    const albumId = 2;

    albumService
      .deleteAlbum(albumId)
      .subscribe((data: any) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/user/deleteAlbum/'+ albumId);

      expect(req.request.method).toEqual('DELETE');

      req.flush(testData);
  });




  



  
  // it('#getFriendship should show friendship status', () => {
  //   let userId = 1;
  //   let userIdP = null;
  //   const testData = 'Friends';
  //   const friendId = 2

  //   albumService
  //     .getFriendship(friendId)
  //     .subscribe((data) => expect(data).toEqual(testData));

  //     const req = httpController.expectOne('http://localhost:8080/api/friendship/' + userIdP + '/' + friendId);

  //     expect(req.request.method).toEqual('GET');

  //     req.flush(testData);
  // });

  // it('#getFriends should show friends', () => {
  //   let userId = 1;
  //   let userIdP = null;
  //   const testData = [
  //     {firstName: 'John',
  //     lastName: 'Doe'
  //   }, 
    
  //   {firstName: 'Jay',
  //   lastName: 'Cruz'
  // }
  //   ];

  //   albumService
  //     .getFriends(userId)
  //     .subscribe((data) => expect(data).toEqual(testData));

  //     const req = httpController.expectOne('http://localhost:8080/api/friendslist/' + userIdP);

  //     expect(req.request.method).toEqual('GET');

  //     req.flush(testData);
  // });

});
