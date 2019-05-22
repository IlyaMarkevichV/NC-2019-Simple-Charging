import {Product} from "./product";
import {Injectable} from "@angular/core";

@Injectable()
export class PageDTO{
  list: any[];
  pages: number;
}
