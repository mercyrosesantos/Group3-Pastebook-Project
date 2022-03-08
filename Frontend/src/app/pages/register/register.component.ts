import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  birthDay: string = "";
  gender: string = "";
  mobileNumber: string = "";

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.firstName);
    console.log(this.lastName);
    console.log(this.email);
    console.log(this.password);
    console.log(this.birthDay);
    console.log(this.gender);
    console.log(this.mobileNumber);

  }

}
