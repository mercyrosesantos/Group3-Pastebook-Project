import { Component } from '@angular/core';
import { SessionService } from '@services/session.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pastebook_frontend';
  constructor(
    public sessionService : SessionService,
    public router : Router
  ) {}
  ngOnInit() {
    if (this.sessionService.getUserId() == null) {
      this.router.navigate(['login']);
    }
  }
}
