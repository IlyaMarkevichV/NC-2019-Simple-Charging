import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class UsersPageService{
  constructor(private http: HttpClient){}

  loadUsers(): Observable<any>{
    return this.http.get('api/user/all');
  }

  deleteUser(id: number): Observable<any>{
    return this.http.delete('api/user/delete?id='+id);
  }
}
