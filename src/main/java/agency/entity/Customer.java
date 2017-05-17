package agency.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import javax.validation.constraints.*;
import java.util.Date;

public class Customer {

    @Id @Getter
    private String id;

    @NotNull @Setter @Getter
    private String name;

    @NotNull @Setter @Getter
    private String surname;

    @Setter @Getter
    @NotNull @Email
    @Indexed(unique = true)
    private String email;

    @Setter @Getter
    private Boolean isActive;

    @Past @Setter @Getter
    private Date bornDate;

    @Past @Setter @Getter
    private Date drivingLicenseDate;

    @Past @Setter @Getter
    private Date firstPoliceDate;

    @Setter @Getter @Min(0) @Max(70)
    private int discount;

    @CreatedDate
    @Setter @Getter
    private Date registerAt;
}
