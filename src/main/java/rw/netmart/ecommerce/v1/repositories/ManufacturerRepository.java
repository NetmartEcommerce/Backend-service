package rw.netmart.ecommerce.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.netmart.ecommerce.v1.models.Manufacturer;

import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
}
