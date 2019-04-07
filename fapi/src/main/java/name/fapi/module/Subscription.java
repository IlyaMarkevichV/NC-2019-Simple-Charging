package name.fapi.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private int subId;
    private Date subBegin;
    private Date subEnd;
    private Product product;
    private Wallet wallet;
}
