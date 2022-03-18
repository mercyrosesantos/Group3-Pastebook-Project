import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { NotificationService } from './notification.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';

describe('NotificationService', () => {
  let notificationService: NotificationService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [NotificationService],
    });
    notificationService = TestBed.inject(NotificationService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(notificationService).toBeDefined();
  });

  it('#getNotif should show notification', () => {
    let userId = 1;
    const testData = "Hey!";
    
    notificationService
      .getNotif(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/notifications/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#getUnread should show unread notification/s', () => {
    let userId = 1;
    const testData = "Read the following";
    
    notificationService
      .getUnread(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/notifications' + '/unread/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });

  it('#setAsRead should show unread notification/s', () => {
    let userId = 1;
    const testData = "Clear";
    
    notificationService
      .setAsRead(userId)
      .subscribe((data) => expect(data).toEqual(testData));

      const req = httpController.expectOne(environment.apiUrl + '/notifications' + '/read/' + userId);

      expect(req.request.method).toEqual('GET');

      req.flush(testData);
  });




});
