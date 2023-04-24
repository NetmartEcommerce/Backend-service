package rw.netmart.ecommerce.v1.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.CreateAccountDto;
import rw.netmart.ecommerce.v1.dtos.RegisterAdminDto;
import rw.netmart.ecommerce.v1.models.User;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.services.IUserServices;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/v1/users")
@CrossOrigin
public class UserController {
    private final IUserServices userService;

    @Autowired
    UserController(IUserServices userService){
        this.userService = userService;
    }

    @PostMapping(path="/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody CreateAccountDto user){
     return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(userService.registerUser(user)));
    }

    @PostMapping(path="/register-admin")
    public ResponseEntity<ApiResponse>  registerAdmin(@Valid @RequestBody RegisterAdminDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(userService.registerAdmin(dto)));
    }


}
