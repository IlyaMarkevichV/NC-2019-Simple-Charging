
export class LoginUser{
  login: string;
  password: string;

  get _login():string{
    return this.login;
  }

  set _login(login: string){
    this.login=login;
  }

  get _password():string{
    return this.password;
  }

  set _password(password: string){
    this.password=password;
  }

}
