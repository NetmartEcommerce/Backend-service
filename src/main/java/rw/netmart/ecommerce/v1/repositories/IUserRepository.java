package rw.netmart.ecommerce.v1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.netmart.ecommerce.v1.models.User;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
