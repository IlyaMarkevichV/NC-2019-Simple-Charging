package name.backend.service;

import name.backend.Entities.WalletEntity;

public interface WalletService {
    WalletEntity saveWallet(WalletEntity wallet);

    Iterable<WalletEntity> findAll();

    void deleteWallet(Integer id);
}
