import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService{

  constructor(private http: HttpClient){}

  getProductById(id:number):Observable<any> {
    return this.http.get('api/product/get?id='+id);
  }
}
