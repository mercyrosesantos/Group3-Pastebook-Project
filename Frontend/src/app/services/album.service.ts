import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Album } from '@models/album';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  private getAlbumUrl: string = environment.apiUrl + '/album/';
  private updateAlbumUrl: string = environment.apiUrl + '/user/updateAlbum/';
  private createAlbumUrl: string = environment.apiUrl + '/user/albums/';
  private uploadPhotosUrl: string = environment.apiUrl + '/album/photos/';
  private getAlbumByIdUrl: string = environment.apiUrl + '/albums-view/';

  constructor(
    private http: HttpClient,
  ) { }


//Get album by UserId
  getAlbumByUserId(userId: number): Observable<Album[]> {
    return this.http.get<Album[]>(this.getAlbumUrl + userId);
  }

  //Get album
  getAlbum(albumId: number): Observable<Album> {
    return this.http.get<Album>(this.getAlbumByIdUrl + albumId);
  }

  //update album
  updateAlbum(albumId: number,albumName: string): Observable<Object> {
    return this.http.put(this.updateAlbumUrl,{'albumName' : albumId, 'id':albumName});
  }
  uploadPhotos(formData: FormData) : any{
    let headers = new HttpHeaders();
    headers.set('Accept', "multipart/form-data");
    return this.http.post(this.uploadPhotosUrl, formData, { headers,responseType: 'text'});
  }
  
  createAlbumByUserId(userId: number,albumName: string): Observable<Object> {
    return this.http.post(this.createAlbumUrl,{'user' : {
      'id' : userId
    }, 'albumName':albumName}, {responseType: 'text'});
  }


  
}