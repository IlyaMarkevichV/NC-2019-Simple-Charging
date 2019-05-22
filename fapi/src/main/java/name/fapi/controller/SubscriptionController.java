package name.fapi.controller;

import name.fapi.module.PageDTO;
import name.fapi.module.Subscription;
import name.fapi.security.TokenProvider;
import name.fapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/subs")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private TokenProvider tokenProvider;

    @PreAuthorize("hasRole('ROLE_user')")
        @PostMapping("/save")
        public ResponseEntity<Subscription> saveSubs (@RequestBody Subscription subscription){
            System.out.println(subscription);
            subscription.setSubBegin(LocalDate.now());
            subscription.setSubEnd(LocalDate.now().plusMonths(1));
            return ResponseEntity.ok(subscriptionService.saveSubs(subscription));
        }

    @PreAuthorize("hasRole('ROLE_user')")
        @GetMapping("/all")
        public ResponseEntity<PageDTO> getAllSubs ( @RequestParam(value = "page") int page,
        @RequestHeader("Authorization") String token){
            token = token.substring(token.indexOf(" ") + 1);
            String login = tokenProvider.getUsernameFromToken(token);
            return ResponseEntity.ok(subscriptionService.getAllSubs(page, login));
        }

    @PreAuthorize("hasRole('ROLE_user')")
        @DeleteMapping("/unsub")
        public void unsub ( @RequestParam(value = "id") int id){
            subscriptionService.unsub(id);
        }
}
