import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SessionService } from '@services/session.service';
import { SearchService } from '@services/search.service';
import { concat, Observable } from 'rxjs';
import { User } from '@models/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  // uncomment once other specs are complete:
  // hasToken: boolean = (localStorage.getItem('token') !== null);
  hasToken: boolean = true;
  firstName: String = localStorage.getItem('firstName')!;
  lastName: String = localStorage.getItem('lastName')!;
  fullName: Observable<String> = concat(this.firstName, " ", this.lastName);
  keyword: string = "";
  result: any;

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private searchService: SearchService
  ) {

    sessionService.hasToken.subscribe(hasToken => {
      this.hasToken = hasToken;
      this.firstName = this.sessionService.getFirstName();
      this.lastName = this.sessionService.getLastName();
    })

  }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.keyword);

    this.result = this.searchService.searchAll(this.keyword);

    console.log(this.result);
  }

  logout(): void {
    this.sessionService.clear();
    this.router.navigate(['/login']);
  }

}
