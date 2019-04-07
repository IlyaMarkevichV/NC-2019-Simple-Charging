package name.fapi.controller;

import name.fapi.module.Role;
import name.fapi.module.User;
import name.fapi.service.RoleService;
import name.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> saveUser(){
        User user=new User();
        Role role=new Role();
        role.setRole("test");
        user.setUserBirthDate(Date.valueOf(LocalDate.of(1999, 8,12)));
        user.setUserEmail("qweqwe@qwe.as");
        user.setUserLogin("asda");
        user.setUserName("Ilya");
        user.setUserSurname("Marek");
        user.setUserPassword("qwerty");
        user.setRole(role);
        return ResponseEntity.ok(userService.save(user));

    }
}
