import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { HttpClient } from '@angular/common/http';
import { Photo } from '@models/photo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  private uploadPhotoUrl: string = environment.apiUrl + '/user/photos';
  private getPhotoUrl: string = environment.apiUrl + '/photo/3';

  constructor(
    private http: HttpClient
  ) { }

  //Upload a Photo


  //Get Photo
  getPhoto(): Observable<Photo> {
  return this.http.get<Photo>(this.getPhotoUrl);
  }
  
}
