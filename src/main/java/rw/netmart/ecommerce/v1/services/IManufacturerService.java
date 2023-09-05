package rw.netmart.ecommerce.v1.services;


import org.springframework.web.multipart.MultipartFile;
import rw.netmart.ecommerce.v1.dtos.CreateManufacturerDto;
import rw.netmart.ecommerce.v1.models.Manufacturer;

import java.util.List;
import java.util.UUID;

public interface IManufacturerService {

    Manufacturer createManufacturer(CreateManufacturerDto dto, MultipartFile file);
    Manufacturer updateManufacturer(UUID id, CreateManufacturerDto dto);

    String removeManufacturer(UUID id);

    List<Manufacturer> getManufacturers();
}
