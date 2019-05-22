package name.fapi.controller;

import name.fapi.module.PageDTO;
import name.fapi.module.User;
import name.fapi.module.Wallet;
import name.fapi.security.TokenProvider;
import name.fapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private TokenProvider tokenProvider;

    @PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_company')")
    @GetMapping("/page")
    public ResponseEntity<PageDTO> loadWallets(@RequestHeader("Authorization") String token,
                                                @RequestParam(value = "page") int page){
        token=token.substring(token.indexOf(" ")+1);
        String login = tokenProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(walletService.loadWallets(page, login));
    }

    @PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_company')")
    @PostMapping("/save")
    public ResponseEntity<Wallet> saveWallet(@RequestHeader("Authorization") String token,
                                             @RequestBody Wallet wallet){
        token=token.substring(token.indexOf(" ")+1);
        String login = tokenProvider.getUsernameFromToken(token);
        wallet.setUser(new User());
        wallet.getUser().setUserLogin(login);
        return ResponseEntity.ok(walletService.saveWallet(wallet));
    }

    @PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_company')")
    @DeleteMapping("/delete")
    public void deleteWallet(@RequestParam(value = "id") int id){
        walletService.deleteWallet(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_company')")
    @PostMapping("/update")
    public ResponseEntity<Wallet> updateWallet(@RequestBody Wallet wallet){
        return ResponseEntity.ok(walletService.updateWallet(wallet));
    }

    @PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_company')")
    @PostMapping("/topup")
    public ResponseEntity<Wallet> topUpBalance(@RequestBody Wallet wallet){
        return ResponseEntity.ok(walletService.topUpBalance(wallet));
    }

    @PreAuthorize("hasAnyRole('ROLE_user')")
    @GetMapping("/all")
    public ResponseEntity<List> getAllWallets(@RequestHeader("Authorization") String token){
        token=token.substring(token.indexOf(" ")+1);
        String login = tokenProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(walletService.getAllWallets(login));
    }

    @PreAuthorize("hasAnyRole('ROLE_user')")
    @PostMapping("/transfer")
    public void transfer(@RequestBody List<Wallet> wallets){
        walletService.transfer(wallets);
    }
}
