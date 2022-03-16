import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Photo } from '@models/photo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  private uploadPhotoUrl: string = environment.apiUrl + '/user/photos/';
  private getPhotoUrl: string = environment.apiUrl + '/photo/3';

  constructor(
    private http: HttpClient
  ) { }

  //Upload a Photo


  //Get Photo
  getPhoto(): Observable<Photo> {
  return this.http.get<Photo>(this.getPhotoUrl);
  }

  

  uploadPhoto(formData: FormData) : Observable<Object>{
    let headers = new HttpHeaders();
//this is the important step. You need to set content type as null
  // headers.set('Content-Type', null);
  headers.set('Accept', "multipart/form-data");


    return this.http.post(this.uploadPhotoUrl, formData, { headers });

  }
  
}
