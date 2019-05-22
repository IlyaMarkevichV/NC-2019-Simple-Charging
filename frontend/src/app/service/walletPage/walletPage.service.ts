import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Wallet} from "../../model/wallet";
import {assertNumber} from "@angular/core/src/render3/assert";
import {ok} from "assert";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class WalletPageService {
  
  constructor(private http:HttpClient){}
  
  getWallets(page: number):Observable<any>{
    return this.http.get('/api/wallet/page?page='+page, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

  deleteWallet(walletId: string): Observable<any>{
    return this.http.delete('/api/wallet/delete?id='+ walletId);
  }

  editWallet(wallet: Wallet):Observable<any>{
    return this.http.post('api/wallet/update', wallet);
  }

  topUp(wallet: Wallet):Observable<any>{
    return this.http.post('api/wallet/topup', wallet);
  }

  transfer(wallet: Wallet, transWallet: Wallet) : Observable<any>{
    let wallets: Wallet[] = [wallet, transWallet];
    return this.http.post('api/wallet/transfer', wallets)
  }

  saveWallet(wallet: Wallet): Observable<any>{
    return this.http.post('api/wallet/save', wallet, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }
}
