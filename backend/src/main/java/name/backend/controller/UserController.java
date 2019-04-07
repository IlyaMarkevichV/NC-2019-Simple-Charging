package name.backend.controller;

import name.backend.Entities.RoleEntity;
import name.backend.Entities.UserEntity;
import name.backend.Entities.WalletEntity;
import name.backend.repository.RoleRepository;
import name.backend.service.RoleService;
import name.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity)
    {
        return ResponseEntity.ok(service.saveUser(userEntity));
    }
}
