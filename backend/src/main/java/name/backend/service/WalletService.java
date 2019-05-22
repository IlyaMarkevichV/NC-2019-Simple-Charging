package name.backend.service;

import name.backend.Entities.WalletEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WalletService {
    Page<WalletEntity> getAll(Pageable pageable, String login);
    WalletEntity saveWallet(WalletEntity walletEntity);
    void deleteWallet(int id);
    WalletEntity updateWallet(WalletEntity walletEntity);
    WalletEntity topup(WalletEntity walletEntity);
    List getAllWallets(String login);
    void transfer(List<WalletEntity> walletEntities);
}
