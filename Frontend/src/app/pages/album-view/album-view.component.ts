import { Location } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Album } from '@models/album';
import { Photo } from '@models/photo';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AlbumService } from '@services/album.service';
import { SessionService } from '@services/session.service';

@Component({
  selector: 'app-album-view',
  templateUrl: './album-view.component.html',
  styleUrls: ['./album-view.component.css']
})
export class AlbumViewComponent implements OnInit {

  albumId? : number;
  userId? : number;
  album?: Album;
  currentPhoto? :Photo;
  photoSrc? : string;
  uploadedPhotos? : FileList;
  isOwnProfile : boolean = false;

  constructor(
    private route: ActivatedRoute,
    public albumService : AlbumService,
    public modalService: NgbModal,
    public sessionService: SessionService,
    private location: Location,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.getAlbum();
    })
  }

  getAlbum(){
    this.route.params.subscribe(params => {
      this.albumId = params['id'];
      this.getAlbumInfo();
    })
  }

  getAlbumInfo(){
    this.albumService.getAlbum(this.albumId!).subscribe((response: Album) => {
      this.album = response;
      this.isOwnProfile = this.album.userIdJson == this.sessionService.getUserId();
    },
    (error: HttpErrorResponse) => {
        if (error.status !== 401) {
            return;
        }
        this.sessionService.clear();
        this.router.navigate(['/login']);
    })
  }

  openImage(photo : Photo, content: any) {
    this.currentPhoto = photo;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', size : 'xl'}).result.then((result) => {
    }, (reason) => {});
  }

  openModal(upload: any) {
    this.modalService.open(upload, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {});

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

  uploadImages() {
    if (this.uploadedPhotos!= null && this.uploadedPhotos?.length > 0) {
      var data = new FormData();
      for (var i=0;i<this.uploadedPhotos.length;i ++) {
        data.append('files[]', this.uploadedPhotos[i]);
      }

      data.append('userId', this.sessionService.getUserId());
      data.append('albumId', this.albumId!.toString());
      this.albumService.uploadPhotos(data).subscribe((response: any) => {
        this.getAlbumInfo();
        this.modalService.dismissAll();
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
  
  back(){
    this.location.back();
  }
}
