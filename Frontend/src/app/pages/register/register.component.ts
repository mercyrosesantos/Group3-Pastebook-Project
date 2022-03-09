import { Component, OnInit } from '@angular/core';
import { User } from '@models/user';
import { UserService } from '@services/user.service';
import * as moment from 'moment';
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
  formattedBirthday?: Date;


  constructor(
    private userService: UserService
  ) { }

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
