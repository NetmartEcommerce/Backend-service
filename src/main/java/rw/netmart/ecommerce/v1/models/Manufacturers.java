package rw.netmart.ecommerce.v1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manufacturers")
public class Manufacturers {
    private String name;
    @Id
    @GeneratedValue
    private Long id;


}
