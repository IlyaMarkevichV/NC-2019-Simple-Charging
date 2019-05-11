import {Injectable} from "@angular/core";
import {LoginUser} from "../../model/loginUser";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class LogUserService{

  private token:string;


  constructor(private http:HttpClient, private router:Router){}

  getToken(user:LoginUser){
    let param = JSON.stringify(user);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post('/api/token/generate-token', param, {headers});
  }


}
