package rw.netmart.ecommerce.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.netmart.ecommerce.v1.dtos.CreateAddressDto;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Table(name="address")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String country;

    private String city;

    private String streetName;

    private String buildingName;

    private String apartment;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    public Address(CreateAddressDto dto) {
        this.setCountry(dto.getCountry());
        this.setApartment(dto.getAppartment());
        this.setBuildingName(dto.getBuildingName());
        this.setStreetName(dto.getStreetName());
        this.setCity(dto.getCity());
    }
}
