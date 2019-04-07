package name.fapi.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private int walletId;
    private String walletName;
    private String walletDescr;
    private double walletBalance;
    private User user;
}
