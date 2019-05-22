package name.fapi.service;

import name.fapi.module.PageDTO;
import name.fapi.module.Wallet;

import java.util.List;

public interface WalletService {
    PageDTO loadWallets(int p, String login);
    Wallet saveWallet(Wallet wallet);
    void deleteWallet(int id);
    Wallet updateWallet(Wallet wallet);
    Wallet topUpBalance(Wallet wallet);
    List getAllWallets(String login);
    void transfer(List<Wallet> wallets);
}
