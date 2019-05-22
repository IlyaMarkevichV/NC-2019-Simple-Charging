import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class SubPageService{

  constructor(private http: HttpClient){

  }

  getSubscriptions(page: number): Observable<any>{
    return this.http.get('/api/subs/all?page='+page, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

  unsubscribe(subId: number): Observable<any>{
    return this.http.delete('api/subs/unsub?id='+subId);
  }
}
