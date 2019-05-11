package name.backend.controller;

import name.backend.Entities.RoleEntity;
import name.backend.Entities.UserEntity;
import name.backend.Entities.WalletEntity;
import name.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value="/api/v1/wallet")
public class WalletController {
    @Autowired
    private WalletService service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<WalletEntity> walletController(@RequestParam(name="role_id", defaultValue = "2") Integer id){
        RoleEntity roleEntity=new RoleEntity();
        UserEntity userEntity=new UserEntity();
        WalletEntity walletEntity=new WalletEntity();
        roleEntity.setRole("admin");
        userEntity.setRole(roleEntity);
        userEntity.setUserBirthDate(LocalDate.now());
        userEntity.setUserEmail("asdasd@asdad.as");
        userEntity.setUserLogin("Weesqq");
        userEntity.setUserName("Ilya");
        userEntity.setUserSurname("Marke");
        userEntity.setUserPassword("qwerty");
        walletEntity.setUser(userEntity);
        walletEntity.setWalletBalance(1.3);
        walletEntity.setWalletDescr("qweqwe asdasd asd");
        walletEntity.setWalletName("Wallet");
        WalletEntity wallet = service.saveWallet(walletEntity);
        return ResponseEntity.ok(wallet);
    }

    @RequestMapping(value = "/test-get", method = RequestMethod.GET)
    public ResponseEntity<Iterable<WalletEntity>> showAll() {
        return ResponseEntity.ok(service.findAll(1));
    }

    @RequestMapping(value = "/test-delete", method = RequestMethod.GET)
    public ResponseEntity deleteWallet(@RequestParam(name = "id") Integer id) {
        service.deleteWallet(id);
        return ResponseEntity.ok().build();
    }
}
