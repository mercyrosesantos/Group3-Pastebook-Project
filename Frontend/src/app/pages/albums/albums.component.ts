import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    private route: ActivatedRoute
  ) { }
  userId? : number;
  isOwnProfile : boolean = false;
  photoSrc? : string;
  uploadedPhotos? : FileList;
  albumName?: string;
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userId = params['id'];
      if (this.sessionService.getUserId() == this.userId) {
        this.isOwnProfile;
      }
      this.getAlbums();
    })

  }
  getAlbums() {    
    this.albumService.getAlbumByUserId(this.userId!).subscribe((response: Album[]) => { 
      console.log('response: ' + JSON.stringify(response));
      this.albums = response;

    });

  }
  update(album: any, e?: any) {
    console.log('album: ' + JSON.stringify(album));
    console.log('e: ' + JSON.stringify(e));
    this.albumService.updateAlbum(album.albumName,album.id).subscribe((response: Object) => { 
      console.log('response: ' + JSON.stringify(response));
      this.getAlbums();
    });
  }
  delete(album: any, e?: any) {
    console.log('album: ' + JSON.stringify(album));
    console.log('e: ' + JSON.stringify(e));
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
    this.albumService.createAlbumByUserId(this.sessionService.getUserId(),this.albumName!).subscribe((response: Object) => { 
      console.log('response: ' + JSON.stringify(response));
      this.uploadImages(Number(response));
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
        // this.getUserProfile();
        // this.uploadedNewImage = false;
        // this.modalService.dismissAll();
      })
    }
  }
}
