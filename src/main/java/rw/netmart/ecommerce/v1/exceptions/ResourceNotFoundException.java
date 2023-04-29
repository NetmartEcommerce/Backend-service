package rw.netmart.ecommerce.v1.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
    super(String.format("%s with %s ['%s'] not found", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName){
        super(String.format("%s with not found", resourceName));
        this.resourceName = resourceName;
    }
}
