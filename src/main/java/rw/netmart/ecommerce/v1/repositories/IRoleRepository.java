package rw.netmart.ecommerce.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.netmart.ecommerce.v1.enums.Erole;
import rw.netmart.ecommerce.v1.models.Role;

import java.util.Optional;
import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(Erole role);
}
