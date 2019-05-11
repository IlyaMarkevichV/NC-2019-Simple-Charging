package name.backend.controller;

import name.backend.Entities.UserEntity;
import name.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value = "")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity)
    {
        System.out.println(userEntity);
        return ResponseEntity.ok(service.saveUser(userEntity));
    }

    @GetMapping(value = "/login/{userLogin}")
    public ResponseEntity<UserEntity> getUserByLogin(@PathVariable(name = "userLogin") String login){
        return ResponseEntity.ok(service.findByLogin(login));
    }

    @GetMapping(value = "/username/{login}")
    @ResponseBody
    public ResponseEntity<String> getUsername(@PathVariable String login){
        return ResponseEntity.ok(service.findUsername(login).getUserLogin());
    }
}
