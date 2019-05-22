import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LoginUser} from "../../model/loginUser";
import {LogUserService} from "../../service/login/log.service";
import {TokenStorage} from "../../storage/tokenStorage";
import {Router} from "@angular/router";
import {UserStorage} from "../../storage/userStorage";
import {UserDTO} from "../../model/userDTO";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {assertLessThan} from "@angular/core/src/render3/assert";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-log-in',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  @Output() isAuthorized = new EventEmitter<boolean>();

  user: LoginUser;
  userDTO: UserDTO;

  isAlert: boolean = false;

  form: FormGroup;

  constructor(private service: LogUserService, private tokens: TokenStorage, private router: Router, private userStorage: UserStorage, private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      login: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      password: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]]
    });
    this.user = new LoginUser();
  }


  autorUser() {
    if (this.form.valid) {
      this.autor();
    } else {
      this.validateAllFormFields(this.form);
    }

  }

  autor(){
    this.setFields();
    this.service.getToken(this.user).subscribe(
      data => {
        this.tokens.saveToken(data);
        this.service.getUser().subscribe(data => {
          this.userDTO = data as UserDTO;
          this.userStorage.saveUser(JSON.stringify(this.userDTO));
          this.isAuthorized.emit(true);
          this.isAlert = false;
          window.location.reload();
        })
      }, ()=>{
        this.isAlert = true;
      }
    );
  }

  setFields(){
    this.user.login = this.form.get('login').value;
    this.user.password = this.form.get('password').value;
  }

  isFieldValid(field: string) {
    return this.form.get(field).touched && !this.form.get(field).valid;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      control.markAsTouched({onlySelf: true});
    })
  }
}
