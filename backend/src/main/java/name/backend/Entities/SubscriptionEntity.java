package name.backend.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "subscription", schema = "chargingdb", catalog = "")
public class SubscriptionEntity {
    private int subId;
    private LocalDate subBegin;
    private LocalDate subEnd;
    private ProductEntity product;
    private WalletEntity wallet;

    @Id
    @Column(name = "sub_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    @Basic
    @Column(name = "sub_begin")
    public LocalDate getSubBegin() {
        return subBegin;
    }

    public void setSubBegin(LocalDate subBegin) {
        this.subBegin = subBegin;
    }

    @Basic
    @Column(name = "sub_end")
    public LocalDate getSubEnd() {
        return subEnd;
    }

    public void setSubEnd(LocalDate subEnd) {
        this.subEnd = subEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionEntity that = (SubscriptionEntity) o;

        if (subId != that.subId) return false;
        if (subBegin != null ? !subBegin.equals(that.subBegin) : that.subBegin != null) return false;
        if (subEnd != null ? !subEnd.equals(that.subEnd) : that.subEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subId;
        result = 31 * result + (subBegin != null ? subBegin.hashCode() : 0);
        result = 31 * result + (subEnd != null ? subEnd.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name="sub_product_id")
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "sub_wallet_id")
    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }
}
