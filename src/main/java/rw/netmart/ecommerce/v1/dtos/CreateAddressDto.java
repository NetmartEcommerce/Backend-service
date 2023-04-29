package rw.netmart.ecommerce.v1.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAddressDto {

    @NotBlank
    public String country;

    @NotBlank
    public String city;

    @NotBlank
    public String streetName;

    @NotBlank
    public String buildingName;

    public String appartment;
}
