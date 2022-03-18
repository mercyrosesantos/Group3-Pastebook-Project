import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { ProfileService } from './profile.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { User } from '@models/user';

describe('ProfileService', () => {
  let profileService: ProfileService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProfileService],
    });
    profileService = TestBed.inject(ProfileService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(profileService).toBeDefined();
  });

  it('#getUserTimeline should show current user timeline', () => {
    const userId = 1;
    const pageNo = 1;
    const testData: string | number | boolean | any | null = [];

    profileService
      .getUserTimeline(userId, pageNo)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/timeline/' + userId+ '/' + pageNo);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });


  it('#getUserProfile should show current user profile', () => {
    const userId = 1;
    const testData = new User();

    profileService
      .getUserProfile(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/profile/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getUserProfileByUrl should show current user profile', () => {
    const url = 'johndoe-1';
    const testData = new User();

    profileService
      .getUserProfileByUrl(url)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/' + url);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#createReaction should show reaction', () => {
    const reaction = 'Wow!';
    const testData = 'Like';

    profileService
      .createReaction(reaction)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/reactions');

      expect(req.request.method).toEqual('POST');

      req.flush(testData);
  });

  it('#updateAboutMe should change About Me portion of profile', () => {
    const user = new User;
    const testData = 'Updates in my personality.';

    profileService
      .updateAboutMe(user)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/profile/aboutme');

      expect(req.request.method).toEqual('PUT');

      req.flush(testData);
  });



});
