package name.backend.service.Impl;

import name.backend.Entities.UserEntity;
import name.backend.Entities.WalletEntity;
import name.backend.repository.UserRepository;
import name.backend.repository.WalletRepository;
import name.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<WalletEntity> getAll(Pageable pageable, String login) {
        return repository.findAllByUserUserLogin(pageable, login);
    }

    @Override
    public WalletEntity saveWallet(WalletEntity walletEntity) {
        System.out.println(walletEntity.toString());
        UserEntity userEntity = userRepository.findByUserLogin(walletEntity.getUser().getUserLogin());
        walletEntity.setUser(userEntity);
        return repository.save(walletEntity);
    }

    @Override
    public void deleteWallet(int id) {
        repository.deleteById(id);
    }

    @Override
    public WalletEntity updateWallet(WalletEntity walletEntity) {
        WalletEntity updateWallet=repository.findById(walletEntity.getWalletId()).get();
        updateWallet.setWalletName(walletEntity.getWalletName());
        updateWallet.setWalletDescr(walletEntity.getWalletDescr());
        return repository.save(updateWallet);
    }

    @Override
    public WalletEntity topup(WalletEntity walletEntity) {
        WalletEntity updateWallet=repository.findById(walletEntity.getWalletId()).get();
        updateWallet.setWalletBalance(walletEntity.getWalletBalance());
        return repository.save(updateWallet);
    }

    @Override
    public List getAllWallets(String login) {
        return repository.findAllByUserUserLogin(login);
    }

    @Override
    public void transfer(List<WalletEntity> walletEntities) {
        walletEntities.forEach(wallet->{
            WalletEntity update = repository.findById(wallet.getWalletId()).get();
            update.setWalletBalance(wallet.getWalletBalance());
            repository.save(update);
        });
    }
}
