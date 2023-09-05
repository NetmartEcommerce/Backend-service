package rw.netmart.ecommerce.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.netmart.ecommerce.v1.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Entity()
@Table(name="subcategories")
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory extends InitiatorAudit {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();


}
