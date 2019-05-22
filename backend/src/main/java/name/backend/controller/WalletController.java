package name.backend.controller;

import name.backend.Entities.PageDTO;
import name.backend.Entities.RoleEntity;
import name.backend.Entities.UserEntity;
import name.backend.Entities.WalletEntity;
import name.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value="/api/wallet")
public class WalletController {
    @Autowired
    private WalletService service;

    @GetMapping("/all")
    public ResponseEntity<PageDTO> loadWallets(@RequestParam(value = "page") int page,
                                               @RequestParam(value = "login") String login){
        Page p = service.getAll(new PageRequest(page, 6, new Sort(Sort.Direction.ASC, "walletName")), login);
        return ResponseEntity.ok(new PageDTO(p.getContent(), p.getTotalPages()));
    }

    @PostMapping("/save")
    public ResponseEntity<WalletEntity> saveWallet(@RequestBody WalletEntity walletEntity){
        System.out.println(walletEntity.toString());
        return ResponseEntity.ok(service.saveWallet(walletEntity));
    }

    @DeleteMapping("/delete")
    public void deleteWallet(@RequestParam(value = "id") int id){
        service.deleteWallet(id);
    }

    @PostMapping("/update")
    public ResponseEntity<WalletEntity> updateWallet(@RequestBody WalletEntity walletEntity){
        return ResponseEntity.ok(service.updateWallet(walletEntity));
    }

    @PostMapping("/topup")
    public ResponseEntity<WalletEntity> topUpBalance(@RequestBody WalletEntity walletEntity){
        return ResponseEntity.ok(service.topup(walletEntity));
    }

    @GetMapping("/getall")
    public ResponseEntity<List> getAllWallets(@RequestParam(value = "login") String login){
        return ResponseEntity.ok(service.getAllWallets(login));
    }

    @PostMapping("/transfer")
    public void transfer (@RequestBody List<WalletEntity> wallets){
        service.transfer(wallets);
    }
}
