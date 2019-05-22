import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  product: Product;

  constructor(private activeRoute: ActivatedRoute, private prodService:ProductService) { }

  ngOnInit() {
    this.getProdById();
  }

  getProdById(){
    const id = this.activeRoute.snapshot.params['id'];
    this.prodService.getProductById(id).subscribe(data=>{
      this.product = data as Product;
    })


  }
}
