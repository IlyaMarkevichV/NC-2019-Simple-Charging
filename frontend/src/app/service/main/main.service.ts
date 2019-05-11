import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class MainService{
  private token:string;

  constructor(private http:HttpClient) {

  }

  getUsername():Observable<any>{
    return this.http.get('api/user/username',{headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

}
