package name.backend.controller;

import name.backend.Entities.UserEntity;
import name.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<UserEntity> getUsername(@PathVariable(name = "login") String login){
        return ResponseEntity.ok(service.findByLogin(login));
    }

    @GetMapping(value = "/subs")
    public ResponseEntity<List> getUserSubs(@RequestParam(name = "login") String login){
        return ResponseEntity.ok(service.getUserSubs(login));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestParam(value = "id") int id){
        service.deleteUser(id);
    }

    @GetMapping(value = "check")
    public ResponseEntity<Boolean> check(@RequestParam(value = "login") String login){
        return ResponseEntity.ok(service.check(login));
    }
}
