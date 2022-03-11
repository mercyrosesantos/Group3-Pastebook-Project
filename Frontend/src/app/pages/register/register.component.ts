import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { User } from '@models/user';
import { UserService } from '@services/user.service';
import * as moment from 'moment';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // Error Validations
  firstNameFormControl = new FormControl('', Validators.required);
  lastNameFormControl = new FormControl('', Validators.required);
  passwordFormControl = new FormControl('', [Validators.required, Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$')]);
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  numberFormControl = new FormControl('', [Validators.required, Validators.pattern('^.{8,15}[0-9]*$')]);
  birthdayFormControl = new FormControl('', Validators.required);
  matcher = new MyErrorStateMatcher();

  // Variable Declarations
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  birthDay: string = "";
  gender: string = "";
  mobileNumber: string = "";
  formattedBirthday?: Date;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void { }

  onSubmit() {
    console.log(this.firstName);
    console.log(this.lastName);
    console.log(this.email);
    console.log(this.password);
    console.log(this.birthDay);
    console.log(this.gender);
    console.log(this.mobileNumber);

    let user = new User();
        user.firstName = this.firstName;
        user.lastName = this.lastName;
        user.email = this.email;
        user.password = this.password;
        this.formattedBirthday = moment(this.birthDay, 'YYYY-MM-DD').toDate();
        user.gender = this.gender;
        user.mobileNumber = this.mobileNumber;

        this.register(user);
  }

  //Registration
  register(user: User) {
    this.userService.register(user).subscribe((response: Object) => {
      console.log(response); 
    });
  }
}
