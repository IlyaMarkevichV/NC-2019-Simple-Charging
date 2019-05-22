import {Injectable} from "@angular/core";
import {Product} from "./product";
import {Wallet} from "./wallet";

@Injectable()
export class Subscription {
  subId : number;
  subBegin : Date;
  subEnd : Date;
  product : Product;
  wallet : Wallet;
}
