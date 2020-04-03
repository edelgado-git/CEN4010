package tutor.recruiter.model;

import javax.persistence.*;

@Entity
@Table(name = "ZIP_CODE")
public class Zipcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
