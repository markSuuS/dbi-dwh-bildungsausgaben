package at.htlleonding.dwh.entity.dimensions;

import jakarta.persistence.*;

@Entity
@Table(name = "DIM_YEAR")
public class DimYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Y_ID")
    private Long id;

    @Column(name = "Y_CODE")
    private String code;

    @Column(name = "Y_YEAR")
    private int year;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
