import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LoginUser} from "../../model/loginUser";
import {LogUserService} from "../../service/login/log.service";
import {TokenStorage} from "../../token/tokenStorage";
import {Router} from "@angular/router";

@Component({
  selector: 'app-log-in',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  @Output() isAuthorized = new EventEmitter<boolean>();

  user: LoginUser;

  constructor(private service: LogUserService, private tokens: TokenStorage, private router: Router) {
    this.user=new LoginUser();
  }

  ngOnInit() {
  }


  autorUser() {
    this.service.getToken(this.user).subscribe(
      data => {
        this.tokens.saveToken(data);
        this.isAuthorized.emit(true);
      }
    );

  }
}
