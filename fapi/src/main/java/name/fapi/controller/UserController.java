package name.fapi.controller;

import name.fapi.module.User;
import name.fapi.security.TokenProvider;
import name.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getUsername(@RequestHeader("Authorization") String token) {
        String login = tokenProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(userService.getUsername(login));
    }
}
