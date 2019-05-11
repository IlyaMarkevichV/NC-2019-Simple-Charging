import {Component, OnInit} from '@angular/core';
import {MainPageService} from "../../service/mainPage/mainPage.service";
import {Product} from "../../model/product";
import {PageDTO} from "../../model/pageDTO";

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  search: string;
  page : number;
  itemsPerPage: number;
  totalpages: number;
  direction: number;
  categories:any;
  products:Product[];
  pageDTO: PageDTO;

  constructor(private service:MainPageService) {
    this.page = 0;
    this.itemsPerPage = 6;
    this.direction = 1;

  }

  ngOnInit() {
    this.loadProductsPage(this.page, this.itemsPerPage, this.direction);
  }

  loadProductsPage(page, itemsPerpage, direction){
    this.service.getProducts(page, itemsPerpage, direction).subscribe(data=>{
      this.pageDTO = data as PageDTO ;
      this.products=this.pageDTO.products;
      co
      this.totalpages= this.pageDTO.pages;
      }
    );
  }

  subscribe(id:number){

  }
}
