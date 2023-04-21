package rw.netmart.ecommerce.v1.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rw.netmart.ecommerce.v1.dtos.CreateAccountDto;
import rw.netmart.ecommerce.v1.enums.EUserStatus;
import rw.netmart.ecommerce.v1.enums.Erole;
import rw.netmart.ecommerce.v1.exceptions.BadRequestException;
import rw.netmart.ecommerce.v1.models.User;
import rw.netmart.ecommerce.v1.repositories.IUserRepository;
import rw.netmart.ecommerce.v1.services.IUserServices;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserServices {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, IUserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public void validateNewRegistration(User user){
        if(isNotUnique(user)){
            throw new BadRequestException(String.format("User with email '%s' or phone number '%s' already exists", user.getEmail(), user.getPhoneNumber()));
        }
    }

    public boolean isNotUnique(User user){
        Optional<User> userOptional = this.userRepository.findByEmailOrPhoneNumber(user.getEmail(), user.getPhoneNumber());
        return userOptional.isPresent();
    }
    @Override
    public User registerUser(CreateAccountDto dto) {
        User user = new User();
        String encodePassword = bCryptPasswordEncoder.encode(dto.getPassword());

        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setRole(Erole.USER);
        user.setStatus(EUserStatus.PENDING);
        user.setPhoneNumber(dto.getPhoneNumber());
        validateNewRegistration(user);

        return userRepository.save(user);
    }
}
