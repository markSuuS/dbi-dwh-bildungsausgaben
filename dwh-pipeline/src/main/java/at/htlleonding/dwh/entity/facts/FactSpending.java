package at.htlleonding.dwh.entity.facts;

import jakarta.persistence.*;

@Entity
@Table(name = "FACT_SPENDING")
public class FactSpending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID")
    private Long id;

    private String transactionTypeCode;
    private long amount;

    public Long getId() {
        return id;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
