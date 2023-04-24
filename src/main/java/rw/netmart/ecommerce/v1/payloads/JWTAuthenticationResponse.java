package rw.netmart.ecommerce.v1.payloads;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String accessToken;

    private String tokenType = "Bearer";

    public JWTAuthenticationResponse(String accessToken){
        this.accessToken = accessToken;
    }

}
