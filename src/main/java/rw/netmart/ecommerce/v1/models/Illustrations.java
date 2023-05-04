package rw.netmart.ecommerce.v1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="illustrations")
public class Illustrations {
    @Id
    @GeneratedValue
    private UUID id;

    private String color;


    private String description;


}
