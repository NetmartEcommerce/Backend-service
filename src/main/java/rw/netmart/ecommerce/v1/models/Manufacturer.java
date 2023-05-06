package rw.netmart.ecommerce.v1.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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


}
