package name.fapi.service.impl;

import name.fapi.module.PageDTO;
import name.fapi.module.Wallet;
import name.fapi.service.WalletService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public PageDTO loadWallets(int p, String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/wallet/all?page={page}&login={login}", PageDTO.class, p, login).getBody();
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        RestTemplate restTemplate= new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl+"/api/wallet/save", wallet, Wallet.class).getBody();
    }

    @Override
    public void deleteWallet(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl+"/api/wallet/delete?id={id}", id);
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl+"/api/wallet/update", wallet, Wallet.class).getBody();
    }

    @Override
    public Wallet topUpBalance(Wallet wallet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl+"/api/wallet/topup", wallet, Wallet.class).getBody();
    }

    @Override
    public List getAllWallets(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/wallet/getall?login={login}", List.class, login).getBody();
    }

    @Override
    public void transfer(List<Wallet> wallets) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl+"/api/wallet/transfer", wallets, List.class);
    }


}
