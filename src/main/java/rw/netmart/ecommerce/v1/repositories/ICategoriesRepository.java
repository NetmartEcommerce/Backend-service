package rw.netmart.ecommerce.v1.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.netmart.ecommerce.v1.models.Category;

import java.util.UUID;

@Repository
public interface ICategoriesRepository extends JpaRepository<Category, UUID> {

    boolean existsByName(String name);
}
