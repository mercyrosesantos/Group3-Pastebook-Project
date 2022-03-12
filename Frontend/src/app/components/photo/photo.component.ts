import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Photo } from '@models/photo';
import { PhotoService } from '@services/photo.service';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
  
  photo: Photo = new Photo();
  // uploadForm?: FormGroup;
  photoSrc? : string;
  constructor(
    private photoService: PhotoService
  ) { 
    this.getPhoto();
  }

  ngOnInit(): void {
  }
  onSubmit() {
    // const formData = new FormData();
    // formData.append('file', this.uploadForm.get('profile').value);

    // this.httpClient.post<any>(this.SERVER_URL, formData).subscribe(
    //   (res) => console.log(res),
    //   (err) => console.log(err)
    // );
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
