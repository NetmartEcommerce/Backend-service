package rw.netmart.ecommerce.v1.models;

import lombok.Getter;
import lombok.Setter;
import rw.netmart.ecommerce.v1.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Entity()
@Table(name="subcategories")
public class SubCategory extends InitiatorAudit {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Product> products;


}
