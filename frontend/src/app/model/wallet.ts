import {User} from "./user";
import {Injectable} from "@angular/core";

@Injectable()
export class Wallet{
  walletId: string;
  walletName: string;
  walletDescr: string;
  walletBalance: number;
  user: User;
}
