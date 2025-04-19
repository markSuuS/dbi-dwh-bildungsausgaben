package at.htlleonding.dwh.entity.dimensions;

import jakarta.persistence.*;

@Entity
@Table(name = "DIM_TRANSACTION_TYPE")
public class DimTransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private Long id;

    @Column(name = "T_CODE")
    private String code;

    @Column(name = "T_TRANSACTION_TYPE_DE")
    private String transactionTypeDe;

    @Column(name = "T_TRANSACTION_TYPE_EN")
    private String transactionTypeEn;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransactionTypeDe() {
        return transactionTypeDe;
    }

    public void setTransactionTypeDe(String transactionTypeDe) {
        this.transactionTypeDe = transactionTypeDe;
    }

    public String getTransactionTypeEn() {
        return transactionTypeEn;
    }

    public void setTransactionTypeEn(String transactionTypeEn) {
        this.transactionTypeEn = transactionTypeEn;
    }
}
