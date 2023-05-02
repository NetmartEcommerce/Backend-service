package rw.netmart.ecommerce.v1.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.netmart.ecommerce.v1.enums.Erole;
import rw.netmart.ecommerce.v1.exceptions.ResourceNotFoundException;
import rw.netmart.ecommerce.v1.models.Role;
import rw.netmart.ecommerce.v1.repositories.IRoleRepository;
import rw.netmart.ecommerce.v1.services.IRoleService;

@Service
public class IRoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Autowired
    public IRoleServiceImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findByName(Erole role) {
        return roleRepository.findByName(role).orElseThrow(()->new ResourceNotFoundException("Role", "name", role.toString()));
    }
}
