import {Role} from "./role";

export class User {
  id: string;
  name: string;
  surname: string;
  login: string;
  password: string;
  role: Role;
  dateOfBirth: Date;

  static cloneBase(user: User): User{
    let clonedUser: User=new User();
    clonedUser.id=user.id;
    clonedUser.name=user.name;
    clonedUser.surname=user.surname;
    clonedUser.login=user.login;
    clonedUser.password=user.password;
    clonedUser.role=user.role;
    clonedUser.dateOfBirth=user.dateOfBirth;
    return clonedUser;
  }
}

