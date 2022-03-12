import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'; 

import { SessionService } from '@services/session.service';
import { UserService } from '@services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = "";
  password: string = "";

  constructor(
    private sessionService: SessionService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    console.log('login: ' + this.sessionService.getUserId());
    if (this.sessionService.getUserId() != null) {
      this.router.navigate(['/']);
    }

  }

  onSubmit(): void { 
    this.userService.login(this.email, this.password).subscribe({
      next: this.successfulLogin.bind(this),
      error: this.failedLogin.bind(this)
    });
  }

  successfulLogin(response: Record<string, any>){
    this.sessionService.setEmail(response['email']);
    this.sessionService.setUserId(response['id']);
    this.sessionService.setFirstName(response['firstName']);
    this.sessionService.setLastName(response['lastName']);
    
    // this.sessionService.setIsAdmin(response['isAdmin']);
    this.sessionService.setToken(response['token']);
    this.router.navigate(['']);
  }

  failedLogin(result: Record<string, any>){

    let data: Record<string, any> = result['error'];

    console.log(data);

    if (data['result'] === 'incorrect_credentials') {
      Swal.fire('Login Failed', 'You have entered incorrect credentials, please try again', 'error');
    } else if (data['result'] === 'user_not_found') {
      Swal.fire('Login Failed', 'User does not exist, please try again.', 'error');
    }

  }

}