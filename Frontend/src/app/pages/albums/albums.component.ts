import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Album } from '@models/album';
import { AlbumService } from '@services/album.service';
import { SessionService } from '@services/session.service';

@Component({
  selector: 'app-albums',
  templateUrl: './albums.component.html',
  styleUrls: ['./albums.component.css']
})
export class AlbumsComponent implements OnInit {

  albums: Album[] = [];
  constructor(
    public sessionService : SessionService,
    public albumService : AlbumService,
    private route: ActivatedRoute,
    private router: Router
  ) { }
  userId? : number;
  isOwnProfile : boolean = false;
  photoSrc? : string;
  uploadedPhotos? : FileList;
  albumName?: string;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userId = params['id'];
      if (Number(this.sessionService.getUserId()) == Number(this.userId)) {
        this.isOwnProfile = true;
      }
      this.getAlbums();
    })

  }

  getAlbums() {    
    this.albumService.getAlbumByUserId(this.userId!).subscribe((response: Album[]) => { 
      this.albums = response;
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }

  update(album: any) {
    this.albumService.updateAlbum(album.albumName,album.id).subscribe((response: Object) => { 
      this.getAlbums();
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }

  delete(album:any) {
    this.albumService.deleteAlbum(album.id).subscribe((response: Object) =>{
      this.getAlbums();
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    })
  }

  changeUploadedPhotos(event: any) {
    const element = event.currentTarget as HTMLInputElement;
    this.uploadedPhotos = element.files!;
    if (this.uploadedPhotos) {
      let fileReader = new FileReader();
      let self = this;
        fileReader.onload = function () {
          self.photoSrc = fileReader.result?.toString();
        }
        fileReader.readAsDataURL(this.uploadedPhotos[0]);
    }
  }

  createAlbum() {
    if (this.uploadedPhotos == null) {
      return;
    }
    this.albumService.createAlbumByUserId(this.sessionService.getUserId(),this.albumName!).subscribe((response: Object) => { 
        this.uploadImages(Number(response));
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    });
  }

  uploadImages(albumId: number) {
    if (this.uploadedPhotos!= null && this.uploadedPhotos?.length > 0) {
      var data = new FormData();
      for (var i=0;i<this.uploadedPhotos.length;i ++) {
        data.append('files[]', this.uploadedPhotos[i]);
      }
      data.append('userId', this.sessionService.getUserId());
      data.append('albumId', albumId.toString());
      this.albumService.uploadPhotos(data).subscribe((response: any) => {
        this.getAlbums();
      },
      (error: HttpErrorResponse) => {
          if (error.status !== 401) {
              return;
          }
          this.sessionService.clear();
          this.router.navigate(['/login']);
      })
    }
  }
}
