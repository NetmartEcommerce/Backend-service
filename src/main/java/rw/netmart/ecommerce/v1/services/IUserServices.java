package rw.netmart.ecommerce.v1.services;

import org.hibernate.sql.Update;
import rw.netmart.ecommerce.v1.dtos.CreateAccountDto;
import rw.netmart.ecommerce.v1.dtos.LoginDto;
import rw.netmart.ecommerce.v1.dtos.RegisterAdminDto;
import rw.netmart.ecommerce.v1.dtos.UpdateUserDto;
import rw.netmart.ecommerce.v1.models.Address;
import rw.netmart.ecommerce.v1.models.User;

public interface IUserServices {
    User registerUser(CreateAccountDto user);
    User registerAdmin(RegisterAdminDto dto);

    User getLoggedInUser();

    User getUserByEmail(String email);
    String verifyEmail(String email, String activationCode);

    String deactivateAccount(String email);

    User updateUserDetails(UpdateUserDto user);

    User deleteAccount(String email , String password);


}
