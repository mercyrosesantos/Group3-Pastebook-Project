import { Component, OnInit } from '@angular/core';
import { Photo } from '@models/photo';
import { PhotoService } from '@services/photo.service';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
  
  photo: Photo = new Photo();
  photoSrc? : string;
  constructor(
    private photoService: PhotoService
  ) { 
    this.getPhoto();
  }

  ngOnInit(): void {
  }
  onSubmit() {
  }

    // Get Photo
    getPhoto() {
      this.photoService.getPhoto().subscribe((response: Photo) => {
        this.photo = response;
        if (this.photo.image != undefined) {
          this.photoSrc = "data:image/png;base64," + this.photo.image;
        }
      })
    }

}
