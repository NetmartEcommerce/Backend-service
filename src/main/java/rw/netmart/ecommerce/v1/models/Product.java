package rw.netmart.ecommerce.v1.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SubCategory category;

    private String model;

    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Manufacturer manufacturer;
    private Integer price;

    private Integer discountRate;

    private Integer inStock;

    private Integer sold;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_manufacturer", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "manufacturer_id"))
    private List<Illustration> illustrations;

    public Product(String name, SubCategory category, String model, String description, Manufacturer manufacturer, Integer pricer, Integer discountRate, Integer inStock, Integer sold) {
        this.name = name;
        this.category = category;
        this.model = model;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = pricer;
        this.discountRate = discountRate;
        this.inStock = inStock;
        this.sold = sold;
    }
}
