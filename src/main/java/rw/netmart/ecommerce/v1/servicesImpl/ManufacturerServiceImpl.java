package rw.netmart.ecommerce.v1.servicesImpl;

import org.springframework.stereotype.Service;
import rw.netmart.ecommerce.v1.dtos.CreateManufacturerDto;
import rw.netmart.ecommerce.v1.enums.EManufacturerStatus;
import rw.netmart.ecommerce.v1.exceptions.ResourceNotFoundException;
import rw.netmart.ecommerce.v1.models.Manufacturer;
import rw.netmart.ecommerce.v1.repositories.ManufacturerRepository;
import rw.netmart.ecommerce.v1.services.ManufacturerService;

import java.util.List;
import java.util.UUID;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer createManufacturer(CreateManufacturerDto dto) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(dto.getName());
        manufacturer.setDescription(dto.getDescription());
        manufacturer.setStatus(EManufacturerStatus.ACTIVE);
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }



    @Override
    public Manufacturer updateManufacturer(UUID id, CreateManufacturerDto dto) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Manufacturer"));
        manufacturer.setName(dto.getName());
        manufacturer.setDescription(dto.getDescription());
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    @Override
    public String removeManufacturer(UUID id) {
        manufacturerRepository.deleteById(id);
        return "Successfully removed data!";
    }

    @Override
    public List<Manufacturer> getManufacturers() {
        return manufacturerRepository.findAll();
    }
}
