package rw.netmart.ecommerce.v1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.netmart.ecommerce.v1.enums.EUserStatus;
import rw.netmart.ecommerce.v1.exceptions.BadRequestException;
import rw.netmart.ecommerce.v1.models.User;
import rw.netmart.ecommerce.v1.repositories.IUserRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadByUserId(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return UserPrincipal.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws BadRequestException {
        User user = this.userRepository.findByEmail(email).orElseThrow(()-> new BadRequestException("User with email " + email + " not found!"));
        if(user.getStatus() == EUserStatus.DEACTIVATED)
            throw new BadRequestException("Your email is deactivated, require activation");
        else if(user.getStatus() == EUserStatus.REJECTED)
            throw new BadRequestException("Your account could not be verified!");
        else if(user.getStatus() == EUserStatus.PENDING)
            throw new BadRequestException("Your account is not verified");
        else if(user.getStatus() == EUserStatus.ACTIVE)
            return UserPrincipal.create(user);
        else
            throw new BadRequestException("Invalid user type");
    }


}
