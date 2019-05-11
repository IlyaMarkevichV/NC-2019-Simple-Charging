import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


const TOKEN_KEY = 'AuthToken';
@Injectable()
export class MainPageService {

  constructor(private http: HttpClient) {
  }

  getProducts(page: number, size: number, dir: number): Observable<any> {
    return this.http.get('api/products/page?page=' + page + '&size=' + size + '&dir=' + dir);
  }

}
