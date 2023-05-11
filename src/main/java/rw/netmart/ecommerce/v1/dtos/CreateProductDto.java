package rw.netmart.ecommerce.v1.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.netmart.ecommerce.v1.models.Manufacturer;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    @NotBlank
    private String name;
    private UUID categoryid;

    @NotBlank
    private String model;

    @NotBlank
    private String description;

    private UUID manufacturerId;
    private Integer price;


    private Integer discountRate;
    private Integer inStock;
    private Integer sold;

}
