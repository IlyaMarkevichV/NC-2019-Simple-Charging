import {Role} from "./role";
import {Injectable} from "@angular/core";

@Injectable()
export class UserDTO{
  userId:number;
  userName:string;
  userLogin:string;
  role:Role;
}
