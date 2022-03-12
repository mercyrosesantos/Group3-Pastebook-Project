import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { SessionService } from '@services/session.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  // uncomment once other specs are complete:
  hasToken: boolean = (localStorage.getItem('token') !== null);
  // hasToken: boolean = true;
  firstName: String = this.sessionService.getFirstName();
  lastName: String = this.sessionService.getLastName();
  keyword: string = "";
  userId: string = this.sessionService.getUserId();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private sessionService: SessionService
  ) {

    sessionService.hasToken.subscribe(hasToken => {
      this.hasToken = hasToken;
      this.firstName = this.sessionService.getFirstName();
      this.lastName = this.sessionService.getLastName();
      this.userId = this.sessionService.getUserId();
    })

  }

  ngOnInit(): void {
  }

  onSubmit(){

    console.log(this.keyword);
    this.sessionService.setKeyword(this.keyword);

    let searchUrl = '/search/' + this.keyword;

    this.router.navigate([searchUrl]);

  }

  logout(): void {
    this.sessionService.clear();
    this.router.navigate(['/login']);
  }

}
