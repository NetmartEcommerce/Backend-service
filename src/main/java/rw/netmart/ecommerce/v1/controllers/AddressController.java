package rw.netmart.ecommerce.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.CreateAddressDto;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.services.IAddressService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("api/v1/address")
public class AddressController {

    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path="/create")
    public ResponseEntity<ApiResponse> createAddress(@Valid @RequestBody CreateAddressDto dto){
        System.out.println("dto");
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(addressService.createAddress(dto)));
    }

    @DeleteMapping(path="/delete")
    public ResponseEntity<ApiResponse> deleteAddress(@Valid @RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(addressService.removeAddress(id)));
    }

    @PutMapping(path= "/update")
    public ResponseEntity<ApiResponse> updateAddress(@Valid @RequestBody CreateAddressDto dto, @RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(addressService.updateAddress(id, dto)));
    }

}
