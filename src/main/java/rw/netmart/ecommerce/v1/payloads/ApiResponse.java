package rw.netmart.ecommerce.v1.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;

    private Object data;

    public ApiResponse(boolean success, String message){
        this.message = message;
        this.success = success;
    }

    public ApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(true, data);
    }

    public static ApiResponse fail(Object data) {
        return new ApiResponse(false, data);
    }

    public static ApiResponse success(String message) {
        return new ApiResponse(true, message);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(false, message);
    }

}