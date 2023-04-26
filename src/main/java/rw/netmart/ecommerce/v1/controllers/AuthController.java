package rw.netmart.ecommerce.v1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.LoginDto;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.payloads.JWTAuthenticationResponse;
import rw.netmart.ecommerce.v1.services.IUserServices;
import rw.netmart.ecommerce.v1.utils.JwtTokenUtil;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final IUserServices userService;
    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(IUserServices userService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto userDetails){
        String jwt = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getEmail(), userDetails.getPassword()));
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                jwt = jwtTokenUtil.generateToken(authentication);

            } else {
                System.out.println("Authentication failed for user " + userDetails.getEmail());
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.ok(ApiResponse.success(new JWTAuthenticationResponse(jwt)));
    }
}
