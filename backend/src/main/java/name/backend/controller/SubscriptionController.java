package name.backend.controller;

import name.backend.Entities.PageDTO;
import name.backend.Entities.SubscriptionEntity;
import name.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subs")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/save")
    public ResponseEntity<SubscriptionEntity> saveSubs(@RequestBody SubscriptionEntity subscriptionEntity){
        System.out.println(subscriptionEntity);
        return ResponseEntity.ok(subscriptionService.save(subscriptionEntity));
    }

    @GetMapping("/all")
    public ResponseEntity<PageDTO> getAllSubs(@RequestParam(value = "page") int page,
                                              @RequestParam(value="login") String login){
        Page p= subscriptionService.getAll(new PageRequest(page, 6, new Sort(Sort.Direction.ASC, "subId")), login);
        return ResponseEntity.ok(new PageDTO(p.getContent(), p.getTotalPages()));
    }

    @DeleteMapping("/unsub")
    public void unsub(@RequestParam(value = "id") int id){
        subscriptionService.unsub(id);
    }

}
