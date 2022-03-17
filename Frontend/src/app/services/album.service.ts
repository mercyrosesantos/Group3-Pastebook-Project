import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Album } from '@models/album';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  private getAlbumUrl: string = environment.apiUrl + '/album/';
  private updateAlbumUrl: string = environment.apiUrl + '/user/updateAlbum/';
  private createAlbumUrl: string = environment.apiUrl + '/user/albums/';
  private uploadPhotosUrl: string = environment.apiUrl + '/album/photos/';
  private getAlbumByIdUrl: string = environment.apiUrl + '/albums-view/';
  private deleteAlbumUrl: string = environment.apiUrl + '/user/deleteAlbum/';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }


//Get album by UserId
  getAlbumByUserId(userId: number): Observable<Album[]> {
    return this.http.get<Album[]>(this.getAlbumUrl + userId , {headers : this.sessionService.getHeaders()});
  }

  //Get album
  getAlbum(albumId: number): Observable<Album> {
    return this.http.get<Album>(this.getAlbumByIdUrl + albumId, {headers : this.sessionService.getHeaders()});
  }

  //update album
  updateAlbum(albumId: number,albumName: string): Observable<Object> {
    return this.http.put(this.updateAlbumUrl,{'albumName' : albumId, 'id':albumName}, {responseType: 'text',  headers : this.sessionService.getHeaders()});
  }
  uploadPhotos(formData: FormData) : any{
    let headers = new HttpHeaders();
    headers.set('Accept', "multipart/form-data");
    headers.set('Authorization', `Bearer ${this.sessionService.getToken()}`)
    return this.http.post(this.uploadPhotosUrl, formData, { headers,responseType: 'text'});
  }
  
  //create album
  createAlbumByUserId(userId: number,albumName: string): Observable<Object> {
    return this.http.post(this.createAlbumUrl,{'user' : {
      'id' : userId
    }, 'albumName':albumName}, {responseType: 'text',  headers : this.sessionService.getHeaders()});
  }

  //Delete album
  deleteAlbum(albumId: number):  Observable<Object> {
    return this.http.delete(this.deleteAlbumUrl+albumId, {responseType: 'text',  headers : this.sessionService.getHeaders()});
  }
  
}