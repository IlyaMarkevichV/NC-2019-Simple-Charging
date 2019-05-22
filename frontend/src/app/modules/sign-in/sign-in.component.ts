import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {Role} from "../../model/role";
import {RegistrService} from "../../service/registr/registr.service";
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {root} from "rxjs/internal-compatibility";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  form: FormGroup;

  user: User;
  roles: Role[];

  role: Role;

  constructor(private userService: RegistrService, private formBuilder: FormBuilder, private rout: Router) {
  }

  ngOnInit() {
    this.loadRoles();
    this.form = this.formBuilder.group({
      name: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      surName: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      email: [null, [Validators.required, Validators.pattern(/^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/)]],
      login: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      password: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      dateOfBirth: [null, Validators.required]
    });
    this.user = new User();
    this.user.role = new Role();
    this.user.role.setRole("user", 2);
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

  onSubmit() {
    if (this.form.valid) {
      this.registr();
    } else {
      this.validateAllFormFields(this.form);
    }
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      control.markAsTouched({onlySelf: true});
    })
  }

  registr() {
    this.setFields();
    this.userService.getUserByLogin(this.user.userLogin).subscribe(data => {
      if (!data) {
        this.userService.addUser(this.user).subscribe();
        this.rout.navigate(['home']);
        alert("Done!");
      }
      else {
        alert("This login is already in use");
      }
    })
  }

  setFields() {
    this.user.userName = this.form.get('name').value;
    this.user.userSurname = this.form.get('surName').value;
    this.user.userEmail = this.form.get('email').value;
    this.user.userBirthDate = this.form.get('dateOfBirth').value;
    this.user.userLogin = this.form.get('login').value;
    this.user.userPassword = this.form.get('password').value;
  }

  setRole(role: Role) {
    this.user.role = role;
  }

  loadRoles() {
    this.userService.loadRoles().subscribe(data => {
      this.roles = data as Role[];
      console.log(this.roles);
    });
  }
}

/*roles: Role[];
user: User;
userForm : FormGroup;

constructor(private userService: RegistrService, private rout: Router) {
  this.user= new User();
  this.user.role=new Role();
}

ngOnInit() {
  this.initForm();
  this.loadRoles();
  this.user.role.setRole("user", 2);
}

initForm(){
  this.userForm = new FormGroup({
    userName: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
      Validators.minLength(4),
      Validators.maxLength(35)
    ]),
    userSurName: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
      Validators.minLength(4),
      Validators.maxLength(35)
    ]),
    userEmail: new FormControl('', [
      Validators.required,
      Validators.pattern(/^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/),
      Validators.minLength(4),
      Validators.maxLength(35)
    ]),
    userLogin: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
      Validators.minLength(5),
      Validators.maxLength(35)
    ]),
    userPassword: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(20)
    ]),
    userBirthDate: new FormControl('', [
      Validators.required
    ])
  });
}

registr(){
  this.setFields();
  this.userService.addUser(this.user).subscribe();
}

setFields(){
  this.refreshUser();
  this.user.userName = this.userForm.value.userName;
  this.user.userSurname = this.userForm.value.userSurName;
  this.user.userEmail = this.userForm.value.userEmail;
  this.user.userBirthDate = this.userForm.value.userBirthDate;
  this.user.userLogin = this.userForm.value.userLogin;
  this.user.userPassword = this.userForm.value.userPassword;
}

refreshUser(){
  this.user = new User();
  this.user.role = new Role();
}

onSubmit(){
 this.registr();
}

setRole(role: Role){
  this.user.role = role;
}

loadRoles(){
  this.userService.loadRoles().subscribe(data=>{
    this.roles = data as Role[];
    console.log(this.roles);
  });
}
}*/

