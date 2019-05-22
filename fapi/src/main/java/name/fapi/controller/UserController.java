package name.fapi.controller;

import name.fapi.module.User;
import name.fapi.module.UserDTO;
import name.fapi.security.SecurityJwtConstants;
import name.fapi.security.TokenProvider;
import name.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping(value = "/signup")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println(user);
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping(value = "/username")
    public ResponseEntity<UserDTO> getUserDTO(@RequestHeader(SecurityJwtConstants.HEADER_STRING) String authToken) {
        authToken = authToken.substring(authToken.indexOf(" ")+1);
        String login = tokenProvider.getUsernameFromToken(authToken);
        return ResponseEntity.ok(userService.getUserDTO(login));
    }

    @PreAuthorize("hasRole('ROLE_user')")
    @GetMapping(value = "/subs")
    public ResponseEntity<List> getUserSubs(@RequestHeader(SecurityJwtConstants.HEADER_STRING) String authToken){
        authToken = authToken.substring(authToken.indexOf(" ")+1);
        String login = tokenProvider.getUsernameFromToken(authToken);
        return ResponseEntity.ok(userService.getUserSubs(login));
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping(value = "/all")
    public ResponseEntity<List> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize(("hasRole('ROLE_admin')"))
    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestParam(value = "id") int id){
        userService.deleteUser(id);
    }

    @GetMapping(value = "check")
    public ResponseEntity<Boolean> check(@RequestParam(value = "login") String login){
        return ResponseEntity.ok(userService.check(login));
    }
}
