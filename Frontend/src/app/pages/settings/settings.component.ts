import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '@models/user';
import { ProfileService } from '@services/profile.service';
import { UserService } from '@services/user.service';
import * as moment from 'moment';
import Swal from 'sweetalert2';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: User = new User();

  // Variable Declarations
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  birthDay: string = "";
  gender: string = "";
  mobileNumber: string = "";
  formattedBirthday?: Date;

  // Show/Hide
  showPass: boolean = false;
  showInfo: boolean = false;
  showEmail: boolean = false;

  constructor(
    private userService: UserService,
    private profileService: ProfileService,
    private router: Router,
    private route: ActivatedRoute
  ) { 
    // let userId: number = this.route.snapshot.params['id'];
    // userService.getUser(userId).subscribe((response: Object) => {this.user = response
    // })
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.userService.updateUserInfo(this.user).subscribe((response: Record<string, any>) => {
      if (response['Result'] === 'Updated User Info') {
        Swal.fire({
          title: 'Update Successful',
          text: 'Your Information has been updated Successfully',
          icon: 'success'
        })
      } else if (response['Result'] === 'Updated User Email') {
        Swal.fire({
          title: 'Update Successful',
          text: 'Your Email has been updated Successfully',
          icon: 'success'
        })
      } else if (response['Result'] === 'Updated User Password') {
        Swal.fire({
          title: 'Update Successful',
          text: 'Your Password has been updated Successfully',
          icon: 'success'
        })
      }
    })
  }

  // Toggle Information Field
  toggleInfo() {
    if (this.showInfo == false) {
      this.showInfo = true;
      this.showPass = false;
      this.showEmail = false;
    }
  }

  // Toggle Email Field
  toggleEmail() {
    if (this.showEmail == false) {
      this.showEmail = true;
      this.showPass = false;
      this.showInfo = false;
    }
  }

  // Toggle Password Field
  togglePass() {
    if (this.showPass == false) {
      this.showPass = true;
      this.showInfo = false;
      this.showEmail = false;
    }
  }
}
