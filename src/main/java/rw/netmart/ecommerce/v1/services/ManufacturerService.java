package rw.netmart.ecommerce.v1.services;


import rw.netmart.ecommerce.v1.dtos.CreateManufacturerDto;
import rw.netmart.ecommerce.v1.models.Manufacturer;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {

    Manufacturer createManufacturer( CreateManufacturerDto dto);
    Manufacturer updateManufacturer(UUID id, CreateManufacturerDto dto);

    String removeManufacturer(UUID id);

    List<Manufacturer> getManufacturer();
}
