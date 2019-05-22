package name.backend.Entities;

import javax.persistence.*;

@Entity
@Table(name = "wallet", schema = "chargingdb", catalog = "")
public class WalletEntity {
    private int walletId;
    private String walletName;
    private String walletDescr;
    private double walletBalance;
    private UserEntity user;

    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    @Basic
    @Column(name = "wallet_name")
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @Basic
    @Column(name = "wallet_descr")
    public String getWalletDescr() {
        return walletDescr;
    }

    public void setWalletDescr(String walletDescr) {
        this.walletDescr = walletDescr;
    }

    @Basic
    @Column(name = "wallet_balance")
    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletEntity that = (WalletEntity) o;

        if (walletId != that.walletId) return false;
        if (Double.compare(that.walletBalance, walletBalance) != 0) return false;
        if (walletName != null ? !walletName.equals(that.walletName) : that.walletName != null) return false;
        if (walletDescr != null ? !walletDescr.equals(that.walletDescr) : that.walletDescr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = walletId;
        result = 31 * result + (walletName != null ? walletName.hashCode() : 0);
        result = 31 * result + (walletDescr != null ? walletDescr.hashCode() : 0);
        temp = Double.doubleToLongBits(walletBalance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "wallet_user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
