package rw.netmart.ecommerce.v1.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.CreateManufacturerDto;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.services.ManufacturerService;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createManufacturer(@Valid @RequestBody CreateManufacturerDto dto){
        return ResponseEntity.ok().body(ApiResponse.success(manufacturerService.createManufacturer(dto)));
    }
    @PutMapping("update")
    public ResponseEntity<ApiResponse> updateManufacturer(@Valid @RequestBody CreateManufacturerDto dto, @RequestParam("id") UUID id){
        return ResponseEntity.ok().body(ApiResponse.success(manufacturerService.updateManufacturer(id, dto)));
    }

    @DeleteMapping("update")
    public ResponseEntity<ApiResponse> deleteManufacturer(@RequestParam("id") UUID id){
        return ResponseEntity.ok().body(ApiResponse.success(manufacturerService.removeManufacturer(id)));
    }

    @GetMapping("update")
    public ResponseEntity<ApiResponse> getManufacturers(){
        return ResponseEntity.ok().body(ApiResponse.success(manufacturerService.getManufacturer()));
    }
}
