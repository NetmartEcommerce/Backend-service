package rw.netmart.ecommerce.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.netmart.ecommerce.v1.audits.InitiatorAudit;
import rw.netmart.ecommerce.v1.enums.EManufacturerStatus;
import rw.netmart.ecommerce.v1.fileHandling.File;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "manufacturers")
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer extends InitiatorAudit {
    private String name;

    private String description;

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="file_id")
    private File logo;

    private EManufacturerStatus status =  EManufacturerStatus.ACTIVE;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_manufacturer", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "manufacturer_id"))
    private List<Product> products;


    public Manufacturer(String name, String description) {
        this.name=name;
        this.description = description;
    }
}
