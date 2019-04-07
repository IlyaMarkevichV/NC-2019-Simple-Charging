package name.backend.service.Impl;

import name.backend.Entities.WalletEntity;
import name.backend.repository.WalletRepository;
import name.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository repository;
    @Override
    public WalletEntity saveWallet(WalletEntity wallet) {
        return repository.save(wallet);
    }

    @Override
    public void deleteWallet(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<WalletEntity> findAll() {
        return repository.findAll();
    }
}
