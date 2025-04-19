package at.htlleonding.dwh.entity.dimensions;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DIM_REGION")
public class DimRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_ID")
    private Long id;

    @ElementCollection
    private List<String> codes = new ArrayList<>();

    @Column(name = "R_REGION_DE")
    private String regionDe;

    @Column(name = "R_REGION_EN")
    private String regionEn;

    public Long getId() {
        return id;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public void addCode(String code) {
        codes.add(code);
    }

    public String getRegionEn() {
        return regionEn;
    }

    public void setRegionEn(String regionEn) {
        this.regionEn = regionEn;
    }

    public String getRegionDe() {
        return regionDe;
    }

    public void setRegionDe(String regionDe) {
        this.regionDe = regionDe;
    }
}
