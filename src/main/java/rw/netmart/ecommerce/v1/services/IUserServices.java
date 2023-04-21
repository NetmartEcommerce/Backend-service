package rw.netmart.ecommerce.v1.services;

import rw.netmart.ecommerce.v1.dtos.CreateAccountDto;
import rw.netmart.ecommerce.v1.models.User;

public interface IUserServices {
    User registerUser(CreateAccountDto user);
}
