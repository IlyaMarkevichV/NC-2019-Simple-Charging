import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {Role} from "../../model/role";
import {RegistrService} from "../../service/registr/registr.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  user: User;
  user2: User;

  constructor(private userService: RegistrService, private rout: Router) {
    this.user= new User();
    this.user.role=new Role();
    this.user2= new User();
    this.user2.role=new Role();
  }

  ngOnInit() {
  }

  onSubmit(){
   this.userService.addUser(this.user).subscribe(sub=>{
     this.user2= sub as User;
   });
  }

  setRole1(){
    this.user.role.setRole("user", 2);
    this.user.userEmail="qwe@qw.we";
  }



}

