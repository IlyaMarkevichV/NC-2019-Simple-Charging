import {Injectable} from "@angular/core";
import {UserDTO} from "../model/userDTO";

const USER_KEY = 'UserDTO';

@Injectable()
export class UserStorage{
  constructor(){}

  public signOut(){
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.clear();
  }

  public saveUser(user: string){
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, user)
  }

  public getUser(): string{
    return window.localStorage.getItem(USER_KEY);
  }
}
