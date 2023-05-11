package rw.netmart.ecommerce.v1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.CheckValidCode;
import rw.netmart.ecommerce.v1.dtos.ForgotPasswordDto;
import rw.netmart.ecommerce.v1.dtos.LoginDto;
import rw.netmart.ecommerce.v1.dtos.ResetPassword;
import rw.netmart.ecommerce.v1.models.User;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.payloads.JWTAuthenticationResponse;
import rw.netmart.ecommerce.v1.security.JwtTokenProvider;
import rw.netmart.ecommerce.v1.services.IUserServices;
import rw.netmart.ecommerce.v1.servicesImpl.MailService;
import rw.netmart.ecommerce.v1.utils.Utility;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final IUserServices userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    @Autowired
    public AuthController(IUserServices userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDto signInDTO) throws Exception {
        String jwt = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getEmail(),signInDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(ApiResponse.success(new JWTAuthenticationResponse(jwt)));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody ForgotPasswordDto dto) throws MessagingException {
        User user = userService.getUserByEmail(dto.getEmail());
        user.setActivationCode(Utility.randomUUID(6,0,'N'));
        this.userService.save(user);
        mailService.sendResetPassword(user);
        return ResponseEntity.ok(new ApiResponse(true, "Password reset email sent successfully"));
    }
    @PostMapping("/verify-code")
    public ResponseEntity<ApiResponse> checkCode(@RequestBody CheckValidCode dto){
        return ResponseEntity.ok(new ApiResponse(userService.verifyCode(dto.getEmail(), dto.getActivationCode())));
    }

    @PostMapping(path="/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody @Valid ResetPassword dto){
        User user = this.userService.getUserByEmail(dto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            user.setActivationCode(Utility.randomUUID(6,0,'N'));
            this.userService.save(user);
        return ResponseEntity.ok(new ApiResponse(true,"Password successfully reset"));
    }

}
