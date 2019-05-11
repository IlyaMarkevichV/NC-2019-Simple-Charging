import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";

const TOKEN_KEY = 'AuthToken';
export class Guard implements CanActivate{

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean{
    if (window.localStorage.getItem(TOKEN_KEY) == null){
      return false;
    }
    else
      return true;
  }
}
