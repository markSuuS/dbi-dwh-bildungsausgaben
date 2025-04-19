package at.htlleonding.dwh.entity.facts;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FACT_EDUCATION_SPENDING")
public class FactEducationSpending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ES_ID")
    private Long id;

    @Column(name = "ES_YEAR")
    private int year;

    @Column(name = "ES_REGION_CODE")
    private String regionCode;

    @Column(name = "ES_INSTITUTION")
    private String institution;

    @OneToMany
    @JoinColumn(name = "ES_FACT_SPENDINGS")
    private List<FactSpending> factSpendings = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public List<FactSpending> getFactSpendings() {
        return factSpendings;
    }

    public void setFactSpendings(List<FactSpending> factSpendings) {
        this.factSpendings = factSpendings;
    }

    public void addFactSpendings(FactSpending factSpending) {
        this.factSpendings.add(factSpending);
    }
}
