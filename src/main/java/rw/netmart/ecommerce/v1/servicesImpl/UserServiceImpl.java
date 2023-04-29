package rw.netmart.ecommerce.v1.servicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import rw.netmart.ecommerce.v1.dtos.CreateAccountDto;
import rw.netmart.ecommerce.v1.dtos.RegisterAdminDto;
import rw.netmart.ecommerce.v1.dtos.UpdateUserDto;
import rw.netmart.ecommerce.v1.enums.EUserStatus;
import rw.netmart.ecommerce.v1.enums.Erole;
import rw.netmart.ecommerce.v1.exceptions.BadRequestException;
import rw.netmart.ecommerce.v1.exceptions.ResourceNotFoundException;
import rw.netmart.ecommerce.v1.models.Address;
import rw.netmart.ecommerce.v1.models.User;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.repositories.IUserRepository;
import rw.netmart.ecommerce.v1.services.IUserServices;


import javax.mail.MessagingException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserServices {

    @Value("${admin_registration_key}")
    private String adminRegistrationKey;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IUserRepository userRepository;

    private final MailService mailService;


    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, IUserRepository userRepository, MailService mailService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.mailService = mailService;
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
        dto.getErole();
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setStatus(EUserStatus.PENDING);
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(encodePassword);

        validateNewRegistration(user);
        try{
            mailService.sendAccountVerificationEmail(user);
        }catch (MessagingException e){
            System.out.println(e);
        }
        return userRepository.save(user);
    }

    @Override
    public User registerAdmin(RegisterAdminDto dto) {
        if(!dto.getKey().equals(adminRegistrationKey))
            throw new BadRequestException("Invalid registration key!");

        User user = new User();
        String encodePassword = bCryptPasswordEncoder.encode(dto.getPassword());

        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setPassword(encodePassword);
        user.setPhoneNumber(dto.getPhoneNumber());

        validateNewRegistration(user);

        return userRepository.save(user);
    }

    @Override
    public User getLoggedInUser(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser")
            throw new BadRequestException("You are not logged in, try to log in");
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        }else{
            email =  principal.toString();
        }
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User getUserByEmail(String email) throws BadRequestException{
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User" ,"email", email));
    }

    @Override
    public String verifyEmail(String email, String activationCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            System.out.println(authentication);
            System.out.println("yes");
            // User is authenticated with their actual credentials
        } else {
            System.out.println(authentication);
            System.out.println("no");
            // User is not authenticated or authenticated anonymously
        }
        User user = getUserByEmail(email);
        if(Objects.equals(user.getActivationCode(), activationCode)){
            user.setStatus(EUserStatus.ACTIVE);
            userRepository.save(user);
            return "Account successfully verified";
        }
        return "Could not verify account!";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public String deactivateAccount(String email) {
        User user = getUserByEmail(email);
        user.setActivationCode(email);
        userRepository.save(user);
        return "Account with email " + email + " has been deactivated!";
    }

    @Override
    public User updateUserDetails(UpdateUserDto userdto){
        User user = getUserByEmail(userdto.getEmail());
       user.setEmail(user.getEmail());
       user.setFirstName(user.getFirstName());
       user.setLastName(user.getLastName());
       user.setPhoneNumber(user.getPhoneNumber());
       userRepository.save(user);
       return user;
    }





    @Override
    public User deleteAccount(String email, String password) {
        User user = getUserByEmail(email);
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            userRepository.delete(user);
            return user;
        }
        throw new BadRequestException("Could not perform task!");
    }


}
