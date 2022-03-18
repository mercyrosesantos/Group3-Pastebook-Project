import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router';
import { User } from '@models/user';
import { SessionService } from '@services/session.service';
import { UserService } from '@services/user.service';
import * as moment from 'moment';
import Swal from 'sweetalert2';
import * as CryptoJs from 'crypto-js';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // Variable Declarations
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  encryptedPassword: string = "";
  birthDay: string = "";
  gender: string = "";
  mobileNumber: string = "";
  formattedBirthday?: Date;

  constructor(
    private userService: UserService,
    private router: Router,
    private sessionService: SessionService
  ) { }

  ngOnInit(): void { }

  encrypt(){
    this.encryptedPassword = CryptoJs.AES.encrypt(this.password,this.email).toString();
  }

  onSubmit() {
    let user = new User();
        user.firstName = this.firstName;
        user.lastName = this.lastName;
        user.email = this.email;
        user.password = this.password;
        this.formattedBirthday = new Date(Date.parse(this.birthDay));
        user.birthDay = this.formattedBirthday;
        user.gender = this.gender;
        user.mobileNumber = this.mobileNumber;
        this.register(user);
  }

  //Registration
  register(user: User) {
    this.userService.register(user).subscribe((response: Object) => {
      this.showSuccess(response);
      
    },
    (error: HttpErrorResponse) => {
      this.showError(error);
    });
     
   
  }

  successfulLogin(response: Record<string, any>){
    this.sessionService.setEmail(response['email']);
    this.sessionService.setUserId(response['id']);
    this.sessionService.setFirstName(response['firstName']);
    this.sessionService.setLastName(response['lastName']);
    this.sessionService.setUrl(response['url']);
    this.sessionService.setToken(response['token']);
    this.router.navigate(['']);
  }

  showSuccess(response: any) {
    var self = this;
    Swal.fire({
      title : 'Register Succesful',
      text : 'Successful',
      icon: "success"
    }).then(function() {
      self.router.navigate(['/login'])
    });
  }

  showError(error: HttpErrorResponse) {
    Swal.fire({
      title : 'Error in Registration',
      text : error.error['Result'],
      icon: "error"
    });
  }
}
