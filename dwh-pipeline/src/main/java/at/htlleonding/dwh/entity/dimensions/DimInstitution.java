package at.htlleonding.dwh.entity.dimensions;

import jakarta.persistence.*;

@Entity
@Table(name = "DIM_INSTITUTION")
public class DimInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_ID")
    private Long id;

    @Column(name = "I_CODE_1")
    private String code1;

    @Column(name = "I_CODE_2")
    private String code2;

    @Column(name = "I_INSTITUTION_DE")
    private String institutionDe;

    @Column(name = "I_INSTITUTION_EN")
    private String institutionEn;

    public Long getId() {
        return id;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getInstitutionDe() {
        return institutionDe;
    }

    public void setInstitutionDe(String institutionDe) {
        this.institutionDe = institutionDe;
    }

    public String getInstitutionEn() {
        return institutionEn;
    }

    public void setInstitutionEn(String institutionEn) {
        this.institutionEn = institutionEn;
    }
}
