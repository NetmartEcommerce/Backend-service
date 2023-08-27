package rw.netmart.ecommerce.v1.models;

import lombok.Getter;
import lombok.Setter;
import rw.netmart.ecommerce.v1.enums.EManufacturerStatus;

import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "manufacturers")
public class Manufacturer {
    private String name;

    private String description;

    @Id
    @GeneratedValue
    private UUID id;

    private File logo;

    private EManufacturerStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_manufacturer", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "manufacturer_id"))
    private List<Product> products;


}
