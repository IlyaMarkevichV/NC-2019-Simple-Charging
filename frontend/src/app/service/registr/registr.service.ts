import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../model/user";

@Injectable({
  providedIn: 'root'
})
export class RegistrService {

  constructor(private http:HttpClient) { }

  addUser(user: User): Observable<User>
  {
    return this.http.post<User>('/api/user/signup', user);
  }

  loadRoles(): Observable<any>{
    return this.http.get('api/role/all');
  }

  getUserByLogin(login: string): Observable<any>{
    return this.http.get<User>('/api/user/check?login='+login);
  }
}
