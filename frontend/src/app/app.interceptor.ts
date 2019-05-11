import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorage} from "./token/tokenStorage";
import {Router} from "@angular/router";
import {tap} from "rxjs/operators";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn:'root'
})
export class Interceptor implements HttpInterceptor{

  constructor(private token: TokenStorage, private router: Router) {}



  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Interceptor works");
    let authreq=req;
    console.log(this.token.getToken());
    if(this.token.getToken()!=null){
      authreq=req.clone({headers:req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())})
    }
    console.log(authreq);
    return next.handle(authreq).pipe(tap((event: HttpEvent<any>)=>{
      if(event instanceof HttpResponse){
        console.log("Interceptor log responce", event);
      }
    },(err:any)=> {
        console.log('req url :: ' + req.url);
        console.error("Interceptor log error", err);
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['login']);
          }
        }
      }
      ));
  }

}
