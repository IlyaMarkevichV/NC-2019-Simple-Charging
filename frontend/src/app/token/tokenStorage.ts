import {Injectable} from "@angular/core";

const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage{
  constructor(){}

  public signOut(){
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }
  
  public saveToken(token: any){
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token.token)
  }

  public getToken(): string{
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
}
