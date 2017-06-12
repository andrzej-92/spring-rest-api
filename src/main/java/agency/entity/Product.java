package agency.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "products")
@EnableMongoAuditing
public class Product {

    @Id @Getter
    private String id;

    @NotNull @Setter @Getter
    private String name;

    @NotNull @Setter @Getter
    private String description;

    @NotNull @Setter @Getter @Min(0)
    private Double pricePerMonth;

    @NotNull @Setter @Getter @Min(0)
    private Double pricePerQuarter;

    @NotNull @Setter @Getter @Min(0)
    private Double pricePerHalfYear;

    @NotNull @Setter @Getter @Min(0)
    private Double pricePerYear;

    @Setter @Getter
    private Boolean isActive;

    @CreatedDate
    @Setter @Getter
    private Date createdAt;

    @LastModifiedDate
    @Setter @Getter
    private Date updatedAt;
}
