package name.fapi.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private int subId;
    private LocalDate subBegin;
    private LocalDate subEnd;
    private Product product;
    private Wallet wallet;
}
