package rw.netmart.ecommerce.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.netmart.ecommerce.v1.models.SubCategory;

import java.util.UUID;

public interface ISubCategoriesRepository extends JpaRepository<SubCategory, UUID> {
}
