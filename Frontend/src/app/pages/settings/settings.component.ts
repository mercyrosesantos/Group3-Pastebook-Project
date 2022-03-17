import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { User } from '@models/user';
import { ProfileService } from '@services/profile.service';
import { SessionService } from '@services/session.service';
import { UserService } from '@services/user.service';
import * as moment from 'moment';

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
  password: string = "";
  birthDay: string = "";
  gender: string = "";
  mobileNumber: string = "";
  formattedBirthday?: Date;

  userInfo: User = new User;
  userId: number = this.sessionService.getUserId();

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

    this.userService.updateUser(this.userInfo).subscribe();

  }

  // Update email
  updateEmail() {

    this.userInfo.id = this.userId;
    this.userInfo.email = this.email;
    this.userInfo.password = this.password;

    this.userService.updateEmail(this.userInfo).subscribe();

  }

  // Update password
  updatePassword() {

    this.userInfo.id = this.userId;
    this.userInfo.password = this.password;

    this.userService.updatePassword(this.userInfo).subscribe();
    
  }

}
