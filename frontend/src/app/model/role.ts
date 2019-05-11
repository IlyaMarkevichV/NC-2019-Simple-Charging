export class Role {
  roleId: number;
  role: string;

  setRole(role : string, id: number){
    this.role=role;
    this.roleId=id;
  }
}
