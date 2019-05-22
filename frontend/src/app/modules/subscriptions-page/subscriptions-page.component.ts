import {Component, OnInit} from '@angular/core';
import {SubPageService} from "../../service/subPage/subPage.service";
import {PageDTO} from "../../model/pageDTO";
import {Subscription} from "../../model/subscription";

@Component({
  selector: 'app-subscriptions-page',
  templateUrl: './subscriptions-page.component.html',
  styleUrls: ['./subscriptions-page.component.css']
})
export class SubscriptionsPageComponent implements OnInit {

  page: number;
  totalpages: number;
  pageDTO: PageDTO;
  subList: Subscription[];

  constructor(private service: SubPageService) {
    this.page=0;
    this.totalpages=0;
    this.subList=null;
  }

  ngOnInit() {
    this.service.getSubscriptions(this.page).subscribe(data=>{
      this.pageDTO=data as PageDTO;
      this.totalpages = this.pageDTO.pages;
      console.log(this.totalpages);
      this.subList= this.pageDTO.list;
      console.log(this.subList);
    })
  }

  unsubscribe(subId: number){
    this.service.unsubscribe(subId).subscribe();
  }
}
