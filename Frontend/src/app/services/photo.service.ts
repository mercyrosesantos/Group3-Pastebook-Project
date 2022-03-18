import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Photo } from '@models/photo';
import { Observable } from 'rxjs';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  private uploadPhotoUrl: string = environment.apiUrl + '/user/photos/';
  private getPhotoUrl: string = environment.apiUrl + '/photo/3';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  //Get Photo
  getPhoto(): Observable<Photo> {
  return this.http.get<Photo>(this.getPhotoUrl, {headers : this.sessionService.getHeaders()}
  );
  }

  
 //Upload a Photo
  uploadPhoto(formData: FormData) : Observable<Object>{
    let headers = this.sessionService.getHeaders();
    headers.set('Accept', "multipart/form-data");
    return this.http.post(this.uploadPhotoUrl, formData, { headers });
  }
  
}
