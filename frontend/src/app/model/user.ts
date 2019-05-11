import {Role} from "./role";

export class User {
  userId: string;
  userName: string;
  userSurname: string;
  userLogin: string;
  userPassword: string;
  role: Role;
  userBirthDate: Date;
  userEmail : string;

  static cloneBase(user: User): User{
    let clonedUser: User=new User();
    clonedUser.userId=user.userId;
    clonedUser.userName=user.userName;
    clonedUser.userSurname=user.userSurname;
    clonedUser.userLogin=user.userLogin;
    clonedUser.userPassword=user.userPassword;
    clonedUser.role=user.role;
    clonedUser.userBirthDate=user.userBirthDate;
    clonedUser.userEmail=user.userEmail;
    return clonedUser;
  }
}

