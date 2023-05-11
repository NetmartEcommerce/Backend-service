package rw.netmart.ecommerce.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.netmart.ecommerce.v1.models.Product;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {

}
