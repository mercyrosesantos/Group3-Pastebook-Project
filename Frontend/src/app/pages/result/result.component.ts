import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { SearchService } from '@services/search.service';
import { User } from '@models/user';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  user?: User[];
  result: any;
  keyword: string = "";

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private searchService: SearchService
  ) {}
  

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.keyword = params['keyword'];
      this.searchService.searchAll(this.keyword).subscribe((response: User) => {
        this.result = response;
      });
      console.log(this.result);
      console.log(this.keyword);
    });

  }

}
