import {Component, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorage} from "../../storage/tokenStorage";
import {MainService} from "../../service/main/main.service";
import {UserDTO} from "../../model/userDTO";
import {UserStorage} from "../../storage/userStorage";
import {Role} from "../../model/role";


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  userDTO:UserDTO;
  isAuthorized:boolean;

  constructor(private service:MainService, private router:Router, private token:TokenStorage, private userStorage: UserStorage) {
  }

  ngOnInit() {
    this.getUser();
  }

  onChange(status:any){
    if(status == true){
      this.getUser();
    }
  }

  getUser(){
    this.userDTO=JSON.parse(this.userStorage.getUser());
    if(this.userDTO!=null){
      this.isAuthorized=true;
    }
    else{
      this.userDTO = new UserDTO();
      console.log(this.userDTO);
      this.userDTO.role = new Role();
      console.log(this.userDTO);
    }
  }

  logOut(){
    this.token.signOut();
    this.isAuthorized = false;
    this.userDTO = null;
  }


}
