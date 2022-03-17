import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { User } from '@models/user';
import { ProfileService } from '@services/profile.service';
import { SessionService } from '@services/session.service';
import { UserService } from '@services/user.service';
import * as moment from 'moment';
import { catchError, of } from 'rxjs';
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

  // Variable Declarations
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  oldPassword: string = "";
  birthDay: string = "";
  gender: string = "";
  mobileNumber: string = "";
  formattedBirthday?: Date;
  newPassword: string = "";
  retypePassword: string = "";
  firstNameLow: string = "";
  lastNameLow: string = "";

  userInfo: User = new User;
  currentUser: User = new User;
  passwordMap = {};
  userId: number = this.sessionService.getUserId();
  stringBirthday?: string;

  // Show/Hide
  showPass: boolean = false;
  showInfo: boolean = false;
  showEmail: boolean = false;

  constructor(
    private userService: UserService,
    private profileService: ProfileService,
    private sessionService: SessionService
  ) { }

  ngOnInit(): void {
    this.getUserProfile();
  }

  onSubmit() {
  }

  // Toggle Information
  toggleInfo() {
    if (this.showInfo == false) {
      this.showInfo = true;
      this.showPass = false;
      this.showEmail = false;
    }
  }

  // Toggle Switch
  toggleEmail() {
    if (this.showEmail == false) {
      this.showEmail = true;
      this.showPass = false;
      this.showInfo = false;
    }
  }

  togglePass() {
    if (this.showPass == false) {
      this.showPass = true;
      this.showInfo = false;
      this.showEmail = false;
    }
  }

  // Update user information
  updateInfo() {
    this.userInfo.id = this.userId;
    this.userInfo.firstName = this.firstName;
    this.userInfo.lastName = this.lastName;
    this.userInfo.birthDay = new Date(Date.parse(this.birthDay));
    this.userInfo.gender = this.gender;
    this.userInfo.mobileNumber = this.mobileNumber;

    this.sessionService.setFirstName(this.firstName);
    this.sessionService.setLastName(this.lastName);

    this.firstNameLow = this.firstName.toLocaleLowerCase();
    this.lastNameLow = this.lastName.toLocaleLowerCase();
    this.sessionService.setUrl(`${this.firstNameLow}${this.lastNameLow}-${this.userId}`);
    
    this.userService.updateUser(this.userInfo).subscribe((response: Object) => {
      this.showSuccess(response);
    },
    (error: HttpErrorResponse) => {
      this.showError(error);
    });
  }

  getUserProfile() {
    this.profileService.getUserProfileByUrl(this.sessionService.getUrl()).subscribe((response: User) => {
      this.currentUser = response;
      this.userId = this.currentUser.id!;
      this.firstName = this.currentUser.firstName!;
      this.lastName = this.currentUser.lastName!;
      this.birthDay = moment(this.currentUser.birthDay).format('YYYY-MM-DD');
      this.gender = this.currentUser.gender!;
      this.mobileNumber = this.currentUser.mobileNumber!;
      this.email = this.currentUser.email!;
  
    })
  }


  // Update email
  updateEmail() {
    this.userInfo.id = this.userId;
    this.userInfo.email = this.email;
    this.userInfo.password = this.oldPassword;
    this.userService.updateEmail(this.userInfo).subscribe((response: Object) => {
      this.showSuccess(response);
    },
    (error: HttpErrorResponse) => {
      this.showError(error);
    });
  }

  // Update password
  updatePassword() {
    this.passwordMap = {
        "id":  this.userId.toString(),
        "oldPassword":  this.oldPassword,
        "newPassword":  this.newPassword,
        "retypePassword":  this.retypePassword,

    }
    this.userService.updatePassword(this.passwordMap).subscribe((response: Object) => {
      this.showSuccess(response);
    },
    (error: HttpErrorResponse) => {
      this.showError(error);
    });
  }

  showSuccess(response: Object) {
    Swal.fire({
      title : 'Update Succesful',
      text : response.toString(),
      icon: "success"
    });
  }

  showError(error: HttpErrorResponse) {
    Swal.fire({
      title : 'Error in updating',
      text : error.error,
      icon: "error"
    });
  }

}
