package rw.netmart.ecommerce.v1.models;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="products")
public class Products {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private SubCategory category;


    private String model;

    private String description;

    private String manufacturer;
    private Integer pricer;

    private Integer discountRate;

    private Integer inStock;

    private Integer sold;

}
