import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { User } from '@models/user';
import { ProfileService } from '@services/profile.service';
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

  // Show/Hide
  showPass: boolean = false;
  showInfo: boolean = false;
  showEmail: boolean = false;

  constructor(
    private userService: UserService,
    private profileService: ProfileService
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
}
