import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Subscription} from "../../model/subscription";
import {Product} from "../../model/product";
import {Category} from "../../model/category";


const TOKEN_KEY = 'AuthToken';
@Injectable()
export class MainPageService {

  constructor(private http: HttpClient) {
  }

  getProducts(page: number, size: number, dir: number): Observable<any> {
    return this.http.get('api/product/page?page=' + page + '&size=' + size + '&dir=' + dir);
  }

  loadWallets():Observable<any>{
    return this.http.get('api/wallet/all', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

  subscribe(subscription: Subscription): Observable<any> {
    return this.http.post('api/subs/save', subscription);
  }

  search(product: string, page: number, size: number, dir: number): Observable<any>{
    return this.http.get('api/product/search?product='+product +'&page=' + page + '&size=' + size + '&dir=' + dir);
  }

  createProduct(product: Product):Observable<any>{
    return this.http.post('api/product/save', product, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

  loadCategories():Observable<any>{
    return this.http.get('api/category/all');
  }

  deleteProd(productId:number): Observable<any>{
    return this.http.delete('api/product/delete?id='+productId);
  }

  getUsersSubs():Observable<any>{
    return this.http.get('api/user/subs', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

  unsubscribe(subId: number): Observable<any>{
    return this.http.delete('api/subs/unsub?id='+subId);
  }

  getByCategoryId(page: number, categoryId: string): Observable<any>{
    return this.http.get('api/category/products?id='+categoryId+'&page='+page);
  }
}
