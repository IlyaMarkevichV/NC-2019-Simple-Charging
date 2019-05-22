package name.backend.billing;

import name.backend.Entities.SubscriptionEntity;
import name.backend.Entities.WalletEntity;
import name.backend.repository.SubscriptionRepository;
import name.backend.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Billing {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Scheduled(fixedDelay = 1000 * 10)
    public void billing() {
        System.out.println(LocalDate.now());
        List<WalletEntity> list = walletRepository.findAllByUserRoleRoleId(2);
        for(int i=0 ; i<list.size(); i++){
            if (subscriptionRepository.findAllByWalletWalletId(list.get(i).getWalletId()).size() != 0) {
                for (SubscriptionEntity sub : subscriptionRepository.findAllByWalletWalletId(list.get(i).getWalletId())) {
                    double sum = sub.getProduct().getProductCost() / 30;
                    if (sub.getSubBegin().equals(LocalDate.now()) || sub.getSubBegin().isBefore(LocalDate.now()) && sub.getSubEnd().isAfter(LocalDate.now())) {
                        if (list.get(i).getWalletBalance() - sum > 0) {
                            list.get(i).setWalletBalance(list.get(i).getWalletBalance() - sum);
                            List<WalletEntity> wal = walletRepository.findAllByUserUserLogin(sub.getProduct().getUser().getUserLogin());
                            wal.get(0).setWalletBalance(wal.get(0).getWalletBalance()+sum);
                            System.out.println(list.get(i).getWalletBalance());
                            System.out.println(wal.get(0).getWalletBalance());
                            walletRepository.save(list.get(i));
                            walletRepository.save(wal.get(0));
                        }
                        else {
                            subscriptionRepository.deleteById(sub.getSubId());
                        }
                    }
                    else{
                        subscriptionRepository.deleteById(sub.getSubId());
                    }
                }
            }
        }
    }
}
