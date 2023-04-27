package rw.netmart.ecommerce.v1.controllers;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.CreateAccountDto;
import rw.netmart.ecommerce.v1.dtos.DeleteAccountDto;
import rw.netmart.ecommerce.v1.dtos.RegisterAdminDto;
import rw.netmart.ecommerce.v1.dtos.UpdateUserDto;
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

    @PostMapping(path = "/verify-email")
    public ResponseEntity<ApiResponse> verifyAccount(@Valid @RequestParam String email, @RequestBody String activationCode){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userService.verifyEmail(email,activationCode)));
    }

    @PostMapping(path="/delete-account")
    public ResponseEntity<ApiResponse> deleteAccount(@Valid @RequestBody DeleteAccountDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userService.deleteAccount(dto.getEmail(), dto.getPassword())));
    }

    @PostMapping(path= "/update-account")
    public ResponseEntity<ApiResponse> updateAccount(@Valid @RequestBody UpdateUserDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userService.updateUserDetails(dto)));
    }
}
