import {Injectable} from "@angular/core";

const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage{
  constructor(){}

  public signOut(){
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.clear();
  }
  
  public saveToken(token: any){
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token.token)
  }

  public getToken(): string{
    return window.localStorage.getItem(TOKEN_KEY);
  }
}
