package rw.netmart.ecommerce.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import rw.netmart.ecommerce.v1.enums.Erole;
import rw.netmart.ecommerce.v1.models.Role;
import rw.netmart.ecommerce.v1.repositories.IRoleRepository;

public interface IRoleService {

    public Role findByName(Erole role);
}
